package works


import breeze.interpolation.LinearInterpolator
import breeze.linalg.DenseVector
import org.opencv.core.{MatOfRect, Point, Scalar}
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.imgproc.Imgproc
import org.opencv.objdetect.CascadeClassifier

class LinearGradientDemo {


  def run() {
    println("\nRunning LinearGraidentDemo")

    val image = Imgcodecs.imread("./src/main/resources/view_gradient/gradient5.jpg")

    val y1 = 0
    val y2 = (image.height()-1)

    // Get the top pixel
    //image.width()
    var top = new Array[Byte](3)
    image.get(y1.toInt, 10, top)

    // Get the bottom pixel
    var bottom = new Array[Byte](3)
    image.get(y2.toInt, 10, bottom)

    // Create linear interpolation for Blue Green and Red
    val yPosition = DenseVector(y1.toDouble, y2.toDouble)
    println("TOP0:" + top(0).toDouble)
    println("BOTTOM0:" + bottom(0).toDouble)

    println("TOP1:" + top(1).toDouble)
    println("BOTTOM1:" + bottom(1).toDouble)

    println("TOP2:" + top(2).toDouble)
    println("BOTTOM2:" + bottom(2).toDouble)

    val yRedColor = DenseVector(top(0).toDouble, bottom(0).toDouble)
    val fRed = LinearInterpolator(yPosition, yRedColor)

    val yGreenColor = DenseVector(top(1).toDouble, bottom(1).toDouble)
    val fGreen = LinearInterpolator(yPosition, yGreenColor)

    val yBlueColor = DenseVector(top(2).toDouble, bottom(2).toDouble)
    val fBlue = LinearInterpolator(yPosition, yBlueColor)

    // Render a new image using these linear interpolation functions
    var data = new Array[Byte](3)
    data(0) = 0.toByte
    data(1) = 0.toByte
    data(2) = 0.toByte
    val gradient = image.clone()
    for (y <- 0 until image.height())
      for (x <- 0 until image.width()) {
        data(0) = fRed(y.toDouble).toByte
        data(1) = fGreen(y.toDouble).toByte
        data(2) = fBlue(y.toDouble).toByte
        gradient.put(y, x, data)
      }



    // Compare the image difference between the source image and the interpolation
    // Display the comparision as an image difference

    val filename = "lineargradient1.png"
    println(String.format("Writing %s", filename))
    Imgcodecs.imwrite(filename, image)

    Imgcodecs.imwrite("lineargradient2.png", gradient)
  }
}