package dockback.rest.repositories

import java.util

import dockback.domain.docker.DockerInfo
import play.api.libs.json._ // Combinator syntax

object DockerInfoJsonToObjectFactory {

  def parseInfo( infoJson: String ) : DockerInfo = {

    val jsInfo: JsValue = Json.parse(infoJson)

    DockerInfoExtractor.extract(jsInfo)

  }

}
