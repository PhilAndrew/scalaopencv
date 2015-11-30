package works

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
//remove if not needed
import scala.collection.JavaConversions._

class KMeansClusterDemo {

  /*def main(args: Array[String]) {
    CVLoader.load()
    val img = Mat.zeros(200, 200, CvType.CV_8UC3)
    Core.rectangle(img, new Point(0, 0), new Point(100, 200), new Scalar(0, 255, 0), -1)
    Core.rectangle(img, new Point(100, 0), new Point(200, 200), new Scalar(0, 0, 255), -1)
    val clusters = cluster(img, 2).get(0)
    ImgWindow.newWindow(img).setTitle("img")

    ImgWindow.newWindow(clusters).setTitle("clusters")
  }*/



  // From https://github.com/badlogic/opencv-fun/blob/master/src/pool/tests/Cluster.java

  def cluster(cutout: Mat, k: Int): List[Mat] = {
    val samples = cutout.reshape(1, cutout.cols() * cutout.rows())
    val samples32f = new Mat()
    samples.convertTo(samples32f, CvType.CV_32F, 1.0 / 255.0)
    val labels = new Mat()
    val criteria = new TermCriteria(TermCriteria.COUNT, 100, 1)
    val centers = new Mat()
    Core.kmeans(samples32f, k, labels, criteria, 1, Core.KMEANS_PP_CENTERS, centers)
    showClusters(cutout, labels, centers)
  }

  private def showClusters(cutout: Mat, labels: Mat, centers: Mat): List[Mat] = {
    centers.convertTo(centers, CvType.CV_8UC1, 255.0)
    centers.reshape(3)
    val clusters = new ArrayList[Mat]()
    for (i <- 0 until centers.rows()) {
      clusters.add(Mat.zeros(cutout.size, cutout.`type`()))
    }
    val counts = new HashMap[Integer, Integer]()
    for (i <- 0 until centers.rows()) counts.put(i, 0)
    var rows = 0
    for (y <- 0 until cutout.rows(); x <- 0 until cutout.cols()) {
      val label = labels.get(rows, 0)(0).toInt
      val r = centers.get(label, 2)(0).toInt
      val g = centers.get(label, 1)(0).toInt
      val b = centers.get(label, 0)(0).toInt
      counts.put(label, counts.get(label) + 1)
      clusters.get(label).put(y, x, b, g, r)
      rows += 1
    }
    println(counts)
    clusters
  }




  def run() {
    println("Running BlobExtractionDemo")

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
    })



    //val labels = new Mat() //new Mat(image.rows(), image.cols(), 1)
    //Imgproc.connectedComponents(image, labels)
/*
    val grayImage = Imgproc.cvCreateImage(cvGetSize(image), IPL_DEPTH_8U, 1)
    cvCvtColor(image, grayImage, CV_BGR2GRAY)
    var mem: CvMemStorage = null
    val contours = new CvSeq()
    var ptr = new CvSeq()
    cvThreshold(grayImage, grayImage, 150, 255, CV_THRESH_BINARY)
    mem = cvCreateMemStorage(0)
    cvFindContours(grayImage, mem, contours, sizeof(classOf[CvContour]), CV_RETR_CCOMP, CV_CHAIN_APPROX_SIMPLE,
      cvPoint(0, 0))
    val rand = new Random()
    ptr = contours
    while (ptr != null) {
      val randomColor = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat())
      val color = CV_RGB(randomColor.getRed, randomColor.getGreen, randomColor.getBlue)
      cvDrawContours(image, ptr, color, CV_RGB(0, 0, 0), -1, CV_FILLED, 8, cvPoint(0, 0))
      ptr = ptr.h_next()
    }
    cvSaveImage(targetPath, image)
*/



  }

}



