package com.iflytek.scala

import scala.collection.immutable._

/**
  * 皮尔逊相关系数
  *
  * Pearson相关系数刻画变量间线性关系的强弱
  *
  */
object pearsonCorrelationSimilarity {

  def pearsonCorrelationSimilarity(arr1: Array[Double], arr2: Array[Double]): Double = {

    // 求和 sum
    val sum_vec1 = arr1.sum
    val sum_vec2 = arr2.sum

    //
    val square_sum_vec1 = arr1.map(x => x * x).sum
    val square_sum_vec2 = arr2.map(x => x * x).sum

    val zipVec = arr1.zip(arr2)

    val product = zipVec.map(x => x._1 * x._2).sum
    val numerator = product - (sum_vec1 * sum_vec2 / arr1.length)

    val dominator = math.pow((square_sum_vec1 - math.pow(sum_vec1, 2) / arr1.length) * (square_sum_vec2 - math.pow(sum_vec2, 2) / arr2.length), 0.5)
    if (dominator == 0) Double.NaN else numerator / (dominator * 1.0)
  }

  def main(args: Array[String]): Unit = {
    val vector:Vector[Double] = Vector(5, 8, 3, 6, 9, 4)
    val vector2:Vector[Double] = Vector(10, 16, 6, 12, 18, 8)
    val score = pearsonCorrelationSimilarity(vector.toArray, vector2.toArray)
    println(score, vector, vector2)

  }

}
