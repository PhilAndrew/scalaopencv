package works

import java.util.concurrent.TimeUnit

import org.opencv.core.{Mat, MatOfRect, Point, Scalar}
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.imgproc.Imgproc
import org.opencv.objdetect.CascadeClassifier



import java.util.ArrayList
import java.util.HashMap
import java.util.List
import java.util.Map
import org.opencv.core.Core
import org.opencv.core.CvType
import org.opencv.core.Mat
import org.opencv.core.Point
import org.opencv.core.Scalar
import org.opencv.core.TermCriteria
import org.opencv.core.Size
import org.opencv.core.MatOfPoint

//remove if not needed
import scala.collection.JavaConversions._

class FindGradientDemo {

  // @todo Intend to find the distinct colors in the image to find the gradients by sorting the colors by
  // @todo color where color difference is the difference and keeping their X,Y location as value

  // @todo Another way could be given a pixel, walk along a path which is the gradient direction going up or down,
  // @todo as long as the gradient is linear or other.
  def findCloseColors(img: Mat) = {
    val w = img.width()
    val h = img.height()

    for (col <- 0 to h)
      for (row <- 0 to w)
      {
        val pixel: Array[Double] = img.get(row,col)
        if (pixel!=null) {
          println(pixel.size + " " + pixel(0) + " " + pixel(1) + " " + pixel(2))
        }
      }
  }

  def run() {
    val src =  Imgcodecs.imread("./src/main/resources/view_gradient/gradient1.png")

    findCloseColors(src)

    val src2 = src.clone()
    val src3 = src.clone()

    val startTime = System.nanoTime()

    val pt1 = new Point(0,0)
    val pt2 = new Point(50,50)
    val color = new Scalar(0,0,0)
    val thickness: Int = 2
    Imgproc.line(src, pt1, pt2, color, thickness)
    val estimatedTime = System.nanoTime() - startTime
    println("Estimated time for task in Milliseconds: " + TimeUnit.NANOSECONDS.toMillis(estimatedTime))

    Core.absdiff(src, src2, src3)

    Imgcodecs.imwrite("lines.png", src3)

  }

}



