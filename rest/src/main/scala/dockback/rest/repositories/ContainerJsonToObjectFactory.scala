package dockback.rest.repositories

import java.util

import dockback.domain.Container
import play.api.libs.json.Reads._
import play.api.libs.json._ // Combinator syntax

object ContainerJsonToObjectFactory extends ContainerExtractor {

  def parseContainer( containerJson: String ) : Container = {

    val jsContainer: JsValue = Json.parse(containerJson)

    extract(jsContainer)

  }

  def parseImages(containersJson: String ) : java.util.List[Container] = {

    val containers = new util.ArrayList[Container]()

    val jsContainers = Json.parse(containersJson)

    for ( image <- jsContainers.as[Array[JsValue]] ) {
      containers.add( extract(image) )
    }

    containers

  }
}
