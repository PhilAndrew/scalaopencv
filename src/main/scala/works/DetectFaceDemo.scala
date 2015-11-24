package works


import org.opencv.core.{MatOfRect, Point, Scalar}
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.imgproc.Imgproc
import org.opencv.objdetect.CascadeClassifier

class DetectFaceDemo {
  def run() {
    println("\nRunning DetectFaceDemo");

    // Create a face detector from the cascade file in the resources
    // directory.

    val faceDetector =
      new CascadeClassifier("./src/main/resources/lbpcascade_frontalface.xml")
    val image = Imgcodecs.imread("./src/main/resources/AverageMaleFace.jpg");

    // Detect faces in the image.
    // MatOfRect is a special container class for Rect.
    val faceDetections = new MatOfRect()
    faceDetector.detectMultiScale(image, faceDetections)

    // Draw a bounding box around each face.
    for ( rect <- faceDetections.toArray()) yield {
      Imgproc.rectangle(image,
        new Point(rect.x, rect.y),
        new Point(rect.x + rect.width, rect.y + rect.height),
        new Scalar(0, 255, 0))
    }

    // Save the visualized detection.
    val filename = "faceDetection.png"
    println(String.format("Writing %s", filename))
    Imgcodecs.imwrite(filename, image)
  }
}