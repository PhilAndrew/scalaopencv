package works

//: ----------------------------------------------------------------------------
//: Copyright (C) 2015 Philip Andrew philip142au@gmail.com.  All Rights Reserved.
//:
//: Dependencies:
//: ?
//: ----------------------------------------------------------------------------

import breeze.interpolation.LinearInterpolator
import breeze.linalg.DenseVector
import org.opencv.core.{Mat, MatOfRect, Point, Scalar}

class GradientDemo {

  // https://github.com/scalanlp/breeze/wiki/Interpolation

  def run(): Unit = {
    val x = DenseVector(0.0, 1.0, 2.0, 3.0)
    val y = DenseVector(2.0, 4.0, 8.0, 5.0)
    val f = LinearInterpolator(x, y)
    val resultA = f(2.1)
    println(resultA)
    val resultB = f(4.9)
    println(resultB)
  }

}
