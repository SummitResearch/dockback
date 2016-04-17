package dockback.docker.CRProxy

import scala.io.Source

class DockbackProperties {
  var defaultHomeDir: String = sys.env("HOME") + "/dockbackWork/dockback/"
  var propertyFileName: String = "config.properties"
  println(defaultHomeDir)
  def getProperties : Option[String] = Source.fromFile(defaultHomeDir + propertyFileName)
  .getLines()
  .find(_.startsWith("dockbackRootDir"))
  //.map(_.replace("propName=", ""))
}