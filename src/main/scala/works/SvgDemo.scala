package works

import java.io.File

import org.apache.batik.apps.rasterizer.{DestinationType, SVGConverter}

//: ----------------------------------------------------------------------------
//: Copyright (C) 2015 Philip Andrew philip142au@gmail.com.  All Rights Reserved.
//:
//: Dependencies:
//: ?
//: ----------------------------------------------------------------------------

class SvgDemo {
  def run() = {
    val r = new SVGConverter()
    val sources = List("./src/main/resources/svg/test.svg").toArray
    r.setSources(sources)
    //r.setDestinationType(DestinationType.?)
    r.setDst(new File("out.png"))
    r.execute()
  }
}
