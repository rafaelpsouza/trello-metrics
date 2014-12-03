name := "trello-metrics"

organization := "br.eng.rafaelsouza"

scalaVersion := "2.10.4"

resolvers += "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases"

libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.10" % "2.1.7" % "test",
  "junit" % "junit" % "4.11" % "test",
  "com.typesafe.scala-logging" %% "scala-logging-slf4j" % "2.1.2",
  "ch.qos.logback" % "logback-classic" % "1.1.2",
  "com.fasterxml.jackson.module" % "jackson-module-scala_2.10" % "2.2.2",
  "com.fasterxml.jackson.datatype" % "jackson-datatype-joda" % "2.2.2",
  "com.sun.xml.bind" % "jaxb-impl" % "2.2.7",
  "javax.xml.bind" % "jaxb-api" % "2.2.7",
  "org.joda" % "joda-convert" % "1.6"
)
