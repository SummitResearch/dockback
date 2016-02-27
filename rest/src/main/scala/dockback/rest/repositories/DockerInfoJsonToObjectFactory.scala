package dockback.rest.repositories

import java.util

import dockback.domain.DockbackImage
import dockback.domain.docker.DockerInfo
import play.api.libs.json.Reads._
import play.api.libs.json._ // Combinator syntax

object DockerInfoJsonToObjectFactory extends DockerInfoExtractor {

  def parseInfo( infoJson: String ) : DockerInfo = {

    val jsInfo: JsValue = Json.parse(infoJson)

    extract(jsInfo)

  }

}
