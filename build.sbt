lazy val root = (project in file("."))
  .settings(
    name := "MongoDB ChangeTracking Demo",
    scalaVersion := "2.12.8",
    version := "1.0"
  )

libraryDependencies ++= Seq(
  "com.lightbend.akka" %% "akka-stream-alpakka-mongodb" % "2.0.2"
)
