package works

import org.opencv.core.{Mat, MatOfRect, Point, Scalar}
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.imgproc.Imgproc
import org.opencv.objdetect.CascadeClassifier

class FloodFillRegionsDemo {
  def run() {
    println("Running FloodFillRegionsDemo")

    val image = Imgcodecs.imread("./src/main/resources/floodfill1.jpg")

    val seedPoint = new Point(50,50)
    val newColor = new Scalar(0,0,255)
    Imgproc.floodFill(image, new Mat(), seedPoint, newColor)

    val filename = "floodfilloutput.png"
    println(String.format("Writing %s", filename))
    Imgcodecs.imwrite(filename, image)
  }

}