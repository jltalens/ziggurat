import sbt.Keys._
import sbt.Resolver


lazy val root = (project in file("."))
.settings(
  name := "ziggurat",
  version := "1.0",
  scalaVersion := "2.11.7",
  libraryDependencies ++= Seq("org.specs2" %% "specs2-core" % "3.7" % "test"),
  libraryDependencies ++= Seq("org.specs2" %% "specs2-mock" % "3.7" % "test"),
  libraryDependencies ++= Seq("com.github.scopt" %% "scopt" % "3.4.0"),
  scalacOptions in Test ++= Seq("-Yrangepos"),
  resolvers += Resolver.sonatypeRepo("public")
)