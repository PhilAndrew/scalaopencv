package works

/*
 * The main runner for the Java demos.
 * Demos whose name begins with "Scala" are written in the Scala language,
 * demonstrating the generic nature of the interface.
 * The other demos are in Java.
 * Currently, all demos are run, sequentially.
 *
 * You're invited to submit your own examples, in any JVM language of
 * your choosing so long as you can get them to build.
 */

import java.awt.image.{DataBufferByte, BufferedImage}
import java.io.File
import javax.imageio.ImageIO

import org.opencv.core.Core

object Main extends App {


  // We must load the native library before using any OpenCV functions.
  // You must load this library _exactly once_ per Java invocation.
  // If you load it more than once, you will get a java.lang.UnsatisfiedLinkError.
  System.loadLibrary(Core.NATIVE_LIBRARY_NAME)

  //new DetectFaceDemo().run()
  //new FloodFillRegionsDemo().run()
  //new KMeansClusterDemo().run()
  //new CannyDemo().run()
  //new FindGradientDemo().run()
  //new GradientDemo().run()
  //new LinearGradientDemo().run()
  //new SvgDemo().run()



  //input image
  var bi1:BufferedImage = null
  bi1 = ImageIO.read(new File("gradient5.jpg"))

  var iu = new SVGImageUtils()

  //Create the svg file using an image
  iu.createSVGFromImage(bi1, "gradient5", bi1.getWidth, bi1.getHeight)

  //Read the svg file to create a png image
  new SvgDemo("gradient5", "blackgradientout").run()

  //get the png that was created
  var bi2:BufferedImage = null
  bi2 = ImageIO.read(new File("blackgradientout.png"))

  //comparing the images
  println(iu.compareMiddlePixelColor(bi1, bi2))
  println(iu.compareImagePixelByPixel(bi1, bi2))



}
