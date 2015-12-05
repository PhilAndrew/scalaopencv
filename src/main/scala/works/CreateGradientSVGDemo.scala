package works

/**
 * Created by Paulo Henrique on 05/12/2015.
 */


import java.io.{OutputStreamWriter, FileOutputStream}

import org.apache.batik.anim.dom.SVGDOMImplementation
import org.apache.batik.svggen.SVGGraphics2D
import org.w3c.dom.svg.SVGDocument

class CreateGradientSVGDemo(color1: String, color2: String) {

  def run(){

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
    stop1.setAttributeNS(null, "style", "stop-color:"+color1+";stop-opacity:1")

    //Create stop 2
    var stop2 = doc.createElementNS(svgNS, "stop")
    stop2.setAttributeNS(null, "offset", "100%")
    stop2.setAttributeNS(null, "style", "stop-color:"+color2+";stop-opacity:1")

    //Atach first stop to the gradient
    gradient.appendChild(stop1)
    //Atach first stop to the gradient
    gradient.appendChild(stop2)
    //Atach gradient to defs
    defs.appendChild(gradient)
    // Attach the rectangle and defs to the root 'svg' element.
    svgRoot.appendChild(defs)
    svgRoot.appendChild(rectangle)

    try{

      var os = new FileOutputStream("./src/main/resources/svg/linearGradient.svg")
      var w = new OutputStreamWriter(os, "iso-8859-1")
      svgGenerator.stream(svgRoot, w)
      println("SVG FILE WAS GENERATED SUCCESSFULLY!")

    }catch{
      case e:Exception => {
        e.printStackTrace()
        println("FAILED GENERATING SVG FILE")

      }
    }
  }

}
