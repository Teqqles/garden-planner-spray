name := "Garden Planner"

organization  := "com.sixfootsoftware"

version       := "0.1"

scalaVersion  := "2.11.8"

crossPaths := false

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  val akkaV = "2.3.9"
  val sprayV = "1.3.3"
  val slickV = "3.0.0"
  Seq(
    "io.spray"            %%  "spray-can"          % sprayV,
    "io.spray"            %%  "spray-json"         % sprayV,
    "io.spray"            %%  "spray-routing"      % sprayV,
    "io.spray"            %%  "spray-testkit"      % sprayV  % "test",
    "com.typesafe.akka"   %%  "akka-actor"         % akkaV,
    "com.typesafe.akka"   %%  "akka-testkit"       % akkaV   % "test",
    "org.scalatest"       %%  "scalatest"          % "3.0.1" % "test",
    "com.typesafe.slick"  %%  "slick"              % slickV,
    "com.typesafe.slick"  %%  "slick-codegen"      % slickV,
    "org.slf4j"           %   "slf4j-nop"          % "1.6.4",
    "mysql"               % "mysql-connector-java" % "latest.release"
  )
}

Revolver.settings
