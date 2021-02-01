package com.iflytek.scala

import org.apache.spark.sql.SparkSession

/**
  * 同现相似度
  *
  * 分母|N(i)|是喜欢物品i的用户数，而分子|N(i)∩N(j)|是同时喜欢物品i和物品j的用户数据
  *
  * 格式：(用户ID，商品ID，评分)
  *
  */
object cooccurrenceSimilarity {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .master("local")
      .appName("cooccurrenceSimilarity")
      .getOrCreate()
    val sc =spark.sparkContext
    val rdd1 = sc.textFile("data/recommend.txt")
      .map(_.split("\\s"))
      .map(x => (x(0), x(1), x(2).toDouble))

    rdd1.foreach(println)

//    提取用户id和物品id
    val rdd2 = rdd1.map(f => (f._1, f._2))
//    (uID,itemID)进行自身的join，获得用户同时购买的商品(item_i,Item_j)
    val rdd3 = rdd2.join(rdd2)

//    res6: Array[((String, String), Int)] =
//    Array(((1,3),2), ((4,3),2), ((3,4),2), ((3,3),3), ((2,2),3), ((1,2),2), ((2,3),1), ((3,2),1), ((4,1),1), ((1,1),3), ((1,4),1), ((2,1),2), ((4,2),2), ((4,4),4), ((2,4),2), ((3,1),2))
    val rdd4 = rdd3.map(f => (f._2, 1))
    val rdd5 = rdd4.reduceByKey(_ + _)

    //获取对于i物品的出现的次数，注意这里是item_i等于Item_j的情况
    //结果((item_i,Item_i),frequence)：
    val rdd6 = rdd5.filter(f => f._1._1 == f._1._2)
    //获取同时购买了物品i和物品j的用户数
    val rdd7 = rdd5.filter(f => f._1._1 != f._1._2)

    //结果(item_i,((item_i,item_j,freqence_ij),fequence_i))：
    val rdd8_1 = rdd6.map(f => (f._1._1, f._2))
    val rdd8_2 = rdd7.map(f => (f._1._1, (f._1._1, f._1._2, f._2)))
    val rdd8 = rdd8_2.join(rdd8_1)

    //结果(item_j,((item_i,item_j,freqence_ij,fequence_i),frequence_j)：
    val rdd9 = rdd8.map(f => (f._2._1._2, (f._2._1._1, f._2._1._2, f._2._1._3, f._2._2)))
    val rdd10 = rdd9.join(rdd8_1)

    //结果(item_i,item_j,freqence_ij,fequence_i,fequence_j)：
    val rdd11 = rdd10.map(f => (f._2._1._1, f._2._1._2, f._2._1._3, f._2._1._4, f._2._2))

    //结果(item_i,item_j,相似度)：
    val rdd12 = rdd11.map(f => (f._1, f._2, f._3 / scala.math.sqrt(f._4 * f._5)))

    rdd12.foreach(println)

  }

}
