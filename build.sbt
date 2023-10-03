lazy val core = (project in file("core")).settings(
  name := "treemaker-core",
  version := "0.1.0",
  scalaVersion := "3.3.0",
  scalacOptions ++= Seq(
    "-no-indent",
    "-old-syntax",
    "-Ykind-projector"
  ),
  libraryDependencies ++= Seq(
    "org.typelevel" %% "cats-core" % "2.9.0",
    "dev.optics"  %%  "monocle-core" % "3.2.0",
    "com.armanbilge" %% "schrodinger" % "0.4-be55d29",
  )
)
