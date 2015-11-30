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

class CannyDemo {

  def run() {
    val src =  Imgcodecs.imread("./src/main/resources/view_gradient/gradient3.png")


    val startTime = System.nanoTime()
    val gray = new Mat()
    val threshold = 12
    Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY)
    Imgproc.blur(gray, gray, new Size(3, 3))
    Imgproc.Canny(gray, gray, threshold, threshold * 3, 3, true)

    Imgcodecs.imwrite("canny1.png", gray)

    val contours = new ArrayList[MatOfPoint]()
    val hierarchy = new Mat()
    Imgproc.findContours(gray, contours, hierarchy, Imgproc.RETR_CCOMP, Imgproc.CHAIN_APPROX_SIMPLE)
    if (hierarchy.size.height > 0 && hierarchy.size.width > 0) {
      var idx = 0
      while (idx >= 0) {
        val rect = Imgproc.boundingRect(contours.get(idx))
        Imgproc.rectangle(src, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
          new Scalar(255, 0, 0))
        idx = hierarchy.get(0, idx)(0).toInt
      }
    }
    val estimatedTime = System.nanoTime() - startTime
    println("Estimated time for task in Milliseconds: " + TimeUnit.NANOSECONDS.toMillis(estimatedTime))

    Imgcodecs.imwrite("canny2.png", src)

    /*
    val image = Imgcodecs.imread("./src/main/resources/svg3.png")

    //val img = Mat.zeros(200, 200, CvType.CV_8UC3)
    //Imgproc.rectangle(img, new Point(0, 0), new Point(100, 200), new Scalar(0, 255, 0), -1)
    //Imgproc.rectangle(img, new Point(100, 0), new Point(200, 200), new Scalar(0, 0, 255), -1)
    val clusters = cluster(image, 5)

    val cluster1 = clusters.get(0)
    println("There are " + clusters.size())

    clusters.zipWithIndex.foreach( (c) => {
      val filename = "bloboutput" + c._2 + ".png"
      println(String.format("Writing %s", filename))
      Imgcodecs.imwrite(filename, c._1)
    })*/
  }

}



