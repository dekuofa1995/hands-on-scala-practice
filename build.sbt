name := "hands-on-scala"
version := "0.1"
scalaVersion := "2.12.7"
import Dependencies._

lazy val commonDeps = Seq(
  logBack, scalaTest
)
lazy val commonSettings = Seq(
  libraryDependencies ++= commonDeps
)

lazy val root = (project in file("."))
  .settings(
    // other settings
    commonSettings
  )
lazy val algorithms = (project in file("6-algorithms"))
  .settings(
    commonSettings
    // other settings
  )

lazy val filesAndSubProcesses = (project in file("7-files-and-subprocesses"))
  .settings(
    commonSettings,
    libraryDependencies += os
    // other settings
  )
