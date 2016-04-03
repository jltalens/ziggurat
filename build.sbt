import sbt.Keys._
import sbt.Resolver
import com.typesafe.sbt.SbtScalariform.ScalariformKeys
import com.typesafe.sbt.SbtScalariform.autoImport._
import scalariform.formatter.preferences._

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots")
)
val pluginsSettings =
  scalariformSettings

val settings = Seq(
  name := "ziggurat",
  version := "0.1",
  scalaVersion := "2.11.7",
  libraryDependencies ++= Seq("org.specs2" %% "specs2-core" % "3.7" % "test"),
  libraryDependencies ++= Seq("org.specs2" %% "specs2-mock" % "3.7" % "test"),
  libraryDependencies ++= Seq("com.github.scopt" %% "scopt" % "3.4.0"),
  scalacOptions in Test ++= Seq("-Yrangepos"),
  ScalariformKeys.preferences := PreferencesImporterExporter.loadPreferences(( file(".") / "formatter.preferences").getPath)
)

packAutoSettings

lazy val root = (project in file("."))
  .settings(
    pluginsSettings ++ settings: _*
  )

