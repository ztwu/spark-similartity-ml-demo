package com.iflytek.scala

/**
  * 余弦相似度
  *
  * 指通过计算两个向量的夹角余弦值来评估它们的相似度
  *
  * 余弦相似度衡量的是维度间取值方向的一致性，注重维度之间的差异，不注重数值上的差异
  *
  */
object cosineSimilarity {

  def cosineSimilarity(x: Array[Double], y: Array[Double]): Double = {

    val member = x.zip(y).map(d => d._1 * d._2).sum

    val temp1 = math.sqrt(x.map(math.pow(_, 2)).sum)
    val temp2 = math.sqrt(y.map(math.pow(_, 2)).sum)

    val denominator = temp1 * temp2
    if (denominator == 0) Double.NaN else member / (denominator * 1.0)

  }

  def main(args: Array[String]): Unit = {
    val vector:Vector[Double] = Vector(5, 8, 3, 6, 9, 4)
    val vector2:Vector[Double] = Vector(5, 2, 6, 3)
    val score = cosineSimilarity(vector.toArray, vector2.toArray)
    println(score, vector, vector2)

  }

}
