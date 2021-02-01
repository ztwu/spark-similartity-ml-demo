package com.iflytek.scala

import scala.collection.immutable._

/**
  * 皮尔逊相关系数
  *
  * pearson相关系数衡量的是线性相关关系。
  * 若r=0，只能说x与y之间无线性相关关系，不能说无相关关系。
  * 相关系数的绝对值越大，相关性越强：
  * 相关系数越接近于1或-1，相关度越强，相关系数越接近于0，相关度越弱
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
