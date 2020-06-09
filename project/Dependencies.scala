import sbt._

object Version {
  val logBackVersion = "1.2.3"
  val scalaTestVersion = "3.1.0"
}

import Version._
object Dependencies {

  val logBack: ModuleID = "ch.qos.logback" % "logback-classic" % logBackVersion

  val scalaTest: ModuleID = "org.scalatest" %% "scalatest" % scalaTestVersion % Test
}
