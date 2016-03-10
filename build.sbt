name := "andrewdata"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "org.scalanlp" %% "breeze" % "0.11.2",
  "org.scalanlp" %% "breeze-natives" % "0.11.2",
  "org.slf4j" % "slf4j-nop" % "1.7.5",
  "org.scala-lang" % "scala-xml" % "2.11.0-M4",
  "org.scalatest" % "scalatest_2.11" % "2.2.6" % "test"
)
    