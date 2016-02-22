name := "ziggurat"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq("org.specs2" %% "specs2-core" % "3.7" % "test")
libraryDependencies += "com.github.scopt" %% "scopt" % "3.4.0"

resolvers += Resolver.sonatypeRepo("public")

scalacOptions in Test ++= Seq("-Yrangepos")