package works

import java.io.File

import org.apache.batik.apps.rasterizer.{DestinationType, SVGConverter}

//: ----------------------------------------------------------------------------
//: Copyright (C) 2015 Philip Andrew philip142au@gmail.com.  All Rights Reserved.
//:
//: Dependencies:
//: ?
//: ----------------------------------------------------------------------------

class SvgDemo(fileNameSVG: String, fileNamePNG: String) {
  def run() = {
    val r = new SVGConverter()
    val sources = List("./src/main/resources/svg/"+fileNameSVG+".svg").toArray
    r.setSources(sources)
    //r.setDestinationType(DestinationType.?)
    r.setDst(new File(fileNamePNG+".png"))
    r.execute()
  }
}
