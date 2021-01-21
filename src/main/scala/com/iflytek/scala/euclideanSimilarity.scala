package com.iflytek.scala

import scala.collection.immutable._

/**
  * 欧几里得距离
  *
  * 指在m维空间中两个点之间的真实距离,或者向量的自然长度(即该点到原点的距离)
  *
  * 欧氏度量的正是数值上的差异性。
  *
  */
object euclideanSimilarity {

  def euclidean(x: Array[Double], y: Array[Double]): Double = {
    /**
      * 二维平面上两点a(x1,y1)与b(x2,y2)间的欧氏距离：
      * (x1-x2)^2 + (y1-y2)^2
      *
      * 三维空间两点a(x1,y1,z1)与b(x2,y2,z2)间的欧氏距离
      * (x1-x2)^2 + (y1-y2)^2+(z1-z2)^2
      *
      */
    math.sqrt(x.zip(y).map(p => p._1 - p._2).map(d => d * d).sum)
  }

  def main(args: Array[String]): Unit = {
    val vector:Vector[Double] = Vector(5, 8, 3, 6, 9, 4)
    val vector2:Vector[Double] = Vector(5, 2, 6, 3)
    val score = euclidean(vector.toArray, vector2.toArray)
    println(score, vector, vector2)

  }

}
