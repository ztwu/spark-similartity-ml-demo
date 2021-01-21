package com.iflytek.scala

/**
  * 修正余弦相似度
  *
  * 修正余弦相似度，指中心化（减去平均值）后再求余弦相似度。
  * 
  */
object adjustedCosineSimilarity {

  def cosineSimilarity(x: Array[Double], y: Array[Double]): Double = {

    val avg = (x.sum + y.sum) / (x.length + y.length)

    val member = x.map(_ - avg).zip(y.map(_ - avg)).map(d => d._1 * d._2).sum

    val temp1 = math.sqrt(x.map(num => math.pow(num - avg, 2)).sum)
    val temp2 = math.sqrt(y.map(num => math.pow(num - avg, 2)).sum)

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
