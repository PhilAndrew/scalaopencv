package works

import java.awt.image.BufferedImage
import java.io.{OutputStreamWriter, FileOutputStream}

import org.apache.batik.anim.dom.SVGDOMImplementation
import org.apache.batik.svggen.SVGGraphics2D
import org.w3c.dom.svg.SVGDocument

/**
 * Created by Paulo Henrique on 06/12/2015.
 */
class SVGImageUtils {

  def createSVGFromImage(img:BufferedImage, fileName: String): Boolean = {

    var leftColor = getLeftMostPixelColor(img)
    var rightColor = getRightMostPixelColor(img)

    var wasCreated = createSVGLinearGradient(fileName, leftColor, rightColor)

    return wasCreated

  }

  /**
   *
   * @param img1 the first image to be compared
   * @param img2 the second image to be compared
   * @return true if the middle pixel color of the two given images is the same
   */
  def compareMiddlePixelColor(img1:BufferedImage, img2: BufferedImage): Boolean = {

    var middlePixelColorImg1 = getMiddlePixelColor(img1)
    var middlePixelColorImg2 = getMiddlePixelColor(img2)

    return middlePixelColorImg1.sameElements(middlePixelColorImg2)

  }

  /**
   *
   * @param img the image where the left most pixel is going to be taken to find its color
   * @return an Array[Integer] with 3 values, representing the RGB code of the color
   */
  def getLeftMostPixelColor(img: BufferedImage): Array[Integer] ={
    var out = getPixelRGB(img, 0, 0)

    return out
  }

  /**
   *
   * @param img the image where the right most pixel is going to be taken to find its color
   * @return an Array[Integer] with 3 values, representing the RGB code of the color
   */
  def getRightMostPixelColor(img: BufferedImage): Array[Integer] = {
    var out = getPixelRGB(img, img.getWidth() - 1, img.getHeight() - 1)

    return out
  }

  /**
   * @param img the image where middle pixel is going to be taken to find its color
   * @return an Array[Integer] with 3 values, representing the RGB code of the color
   */
  def getMiddlePixelColor(img: BufferedImage): Array[Integer] = {
    var out = getPixelRGB(img, img.getWidth()/2, img.getHeight()/2)

    return out
  }

  /**
   * @param img the image where the pixel is going to be taken to find its color
   * @param x coordinate x of the pixel
   * @param y coordinate y of the pixel
   * @return an Array[Integer] with 3 values, representing the RGB code of the color
   */
  def getPixelRGB(img: BufferedImage, x: Integer, y: Integer): Array[Integer] = {
    var argb = img.getRGB(x, y)

    var rgb = Array[Integer] (
      (argb >> 16) & 0xff, //red
      (argb >>  8) & 0xff, //green
      (argb      ) & 0xff  //blue
    )

    return rgb
  }

  /**
   * @param img1 the first image to be compared
   * @param img2 the second image to be compared
   * @return true if the two given images are exactly the same
   */
  def compareImagePixelByPixel(img1:BufferedImage , img2: BufferedImage): Boolean = {

    if (img1.getWidth() == img2.getWidth() && img1.getHeight() == img2.getHeight()) {
        for (x <- 0 to img1.getWidth() - 1) {
            for (y <- 0 to img1.getHeight() - 1) {
                if (img1.getRGB(x, y) != img2.getRGB(x, y))
                    return false
            }
        }
    } else {
        return false
    }
    return true
}

  /**
   * @param fileName the name of the file that will be created
   * @param color1 the first color to make the linear gradient
   * @param color2 the second color to make the linear gradient
   */
  def createSVGLinearGradient(fileName: String, color1: Array[Integer], color2: Array[Integer]): Boolean = {

    var impl = SVGDOMImplementation.getDOMImplementation()
    var svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI
    var doc = impl.createDocument(svgNS, "svg", null).asInstanceOf[SVGDocument]
    var svgGenerator = new SVGGraphics2D(doc)

    // Get the root element (the 'svg' element).
    var svgRoot = svgGenerator.getRoot(doc.getDocumentElement())

    // Set the width and height attributes on the root 'svg' element.
    svgRoot.setAttributeNS(null, "width", "1000")
    svgRoot.setAttributeNS(null, "height", "500")
    svgRoot.setAttributeNS(null, "stroke", "none")

    // Create the rectangle.
    var rectangle = doc.createElementNS(svgNS, "rect")
    //  rectangle.setAttributeNS(null, "x", "10")
    //  rectangle.setAttributeNS(null, "y", "20")
    rectangle.setAttributeNS(null, "width", "1000")
    rectangle.setAttributeNS(null, "height", "500")
    rectangle.setAttributeNS(null, "fill", "url(#grad)")

    //Create defs
    var defs = doc.createElementNS(svgNS, "defs")

    //Create linear gradient
    var gradient = doc.createElementNS(svgNS, "linearGradient")
    gradient.setAttributeNS(null, "id", "grad")
    gradient.setAttributeNS(null, "x1", "0%")
    gradient.setAttributeNS(null, "y1", "0%")
    gradient.setAttributeNS(null, "x2", "100%")
    gradient.setAttributeNS(null, "y2", "0%")

    //Create stop 1
    var stop1 = doc.createElementNS(svgNS, "stop")
    stop1.setAttributeNS(null, "offset", "0%")
    stop1.setAttributeNS(null, "style", "stop-color:"+buildRGBColor(color1)+";stop-opacity:1")

    //Create stop 2
    var stop2 = doc.createElementNS(svgNS, "stop")
    stop2.setAttributeNS(null, "offset", "100%")
    stop2.setAttributeNS(null, "style", "stop-color:"+buildRGBColor(color2)+";stop-opacity:1")

    //Atach first stop to the gradient
    gradient.appendChild(stop1)
    //Atach second stop to the gradient
    gradient.appendChild(stop2)
    //Atach gradient to defs
    defs.appendChild(gradient)
    // Attach the rectangle and defs to the root 'svg' element.
    svgRoot.appendChild(defs)
    svgRoot.appendChild(rectangle)

    try{

      var os = new FileOutputStream("./src/main/resources/svg/"+fileName+".svg")
      var w = new OutputStreamWriter(os, "iso-8859-1")
      svgGenerator.stream(svgRoot, w)
      println("SVG FILE WAS GENERATED SUCCESSFULLY!")
      return true

    }catch{
      case e:Exception => {
        e.printStackTrace()
        println("FAILED GENERATING SVG FILE")
        return false

      }
    }
  }

  /**
   *
   * @param color an Array[Integer] with 3 values, representing the RGB code of the color
   * @return a String representing a RGB code do be used on the construction of the SVG file
   */
  def buildRGBColor(color: Array[Integer]): String = {

    var rgb ="rgb("+color(0)+", "+color(1)+", "+color(2)+")"

    return rgb

  }



}
