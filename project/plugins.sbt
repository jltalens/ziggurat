logLevel := Level.Warn


// Resolvers
//

resolvers += "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"

// Scalariform
//
addSbtPlugin("org.scalariform" % "sbt-scalariform" % "1.6.0")


addSbtPlugin("org.xerial.sbt" % "sbt-pack" % "0.7.9")