
name := "scalaopencv"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies  ++= Seq(
  // other dependencies here
  "org.scalanlp" %% "breeze" % "0.11.2",
  // native libraries are not included by default. add this if you want them (as of 0.7)
  // native libraries greatly improve performance, but increase jar sizes.
  // It also packages various blas implementations, which have licenses that may or may not
  // be compatible with the Apache License. No GPL code, as best I know.
  "org.scalanlp" %% "breeze-natives" % "0.11.2",

  // This is to convert SVG file to PNG
  "org.apache.xmlgraphics" % "xmlgraphics-commons" % "2.0.1",
  "org.apache.xmlgraphics" % "batik-codec" % "1.8",
  "org.apache.xmlgraphics" % "batik-rasterizer" % "1.8",
  "org.apache.xmlgraphics" % "batik-svggen" % "1.8"
)

resolvers ++= Seq(
  // other resolvers here
  // if you want to use snapshot builds (currently 0.12-SNAPSHOT), use this.
  "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
  "Sonatype Releases" at "https://oss.sonatype.org/content/repositories/releases/"
)

