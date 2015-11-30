
Tested on Windows.

1. Download OpenCV
http://opencv.org/downloads.html

2. Extract to some directory, I'm on Windows and extracted OpenCV at
C:\home\software\opencv

3. In the SBT Scala example within this OpenCV copy the JAR file to the JAR directory
Copy the JAR from
C:\home\software\opencv\build\java
TO
This projects lib directory

Actually I already did this.

4. Copy opencv_java300.dll from somewhere to this directory scalaopencv where you type sbt run

5. sbt run



