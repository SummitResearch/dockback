package dockback.rest.repositories

import java.util

import dockback.domain.DockerPartialContainer
import play.api.libs.json.Reads._
import play.api.libs.json._ // Combinator syntax

object PartialContainerJsonToObjectFactory extends PartialContainerExtractor {

  def parseContainer( containerJson: String ) : DockerPartialContainer = {

    val jsContainer: JsValue = Json.parse(containerJson)

    extract(jsContainer)

  }

  def parseContainers(containersJson: String ) : java.util.List[DockerPartialContainer] = {

    val containers = new util.ArrayList[DockerPartialContainer]()

    val jsContainers = Json.parse(containersJson)

    for ( image <- jsContainers.as[Array[JsValue]] ) {
      containers.add( extract(image) )
    }

    containers

  }
}
