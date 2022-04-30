name := "tapir-example"

version := "0.1"

scalaVersion := "2.13.8"

val tapirVersion = "1.0.0-M7"

libraryDependencies ++= Seq(
  "io.d11" %% "zhttp" % "1.0.0.0-RC27",
  "com.softwaremill.sttp.tapir" %% "tapir-core" % tapirVersion,
  "com.softwaremill.sttp.tapir" %% "tapir-json-zio1" % tapirVersion,
  "com.softwaremill.sttp.tapir" %% "tapir-zio1-http-server" % tapirVersion
)
