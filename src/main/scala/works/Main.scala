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
  new CreateGradientSVGDemo("blue", "white").run()



//  var bi1:BufferedImage = null
//  bi1 = ImageIO.read(new File("out2.png"))
//
//  var bi2:BufferedImage = null
//  bi2 = ImageIO.read(new File("out2.png"))
//
//  var iu = new ImageUtils()
//
//  println(iu.compareMiddlePixelColor(bi1, bi2))



}
