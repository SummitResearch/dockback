package dockback.rest.repositories

import java.util
import dockback.domain.Container
import play.api.libs.json.Reads._
import play.api.libs.json._ // Combinator syntax

object ContainerJsonToObjectFactory {

  def parsePartialContainer( containerJson: String ) : Container = {

    val jsContainer: JsValue = Json.parse(containerJson)

    PartialContainerExtractor.extract(jsContainer)

  }

  def parseFullContainer( containerJson: String ) : Container = {

    val jsContainer: JsValue = Json.parse(containerJson)

    FullContainerExtractor.extract(jsContainer)

  }

  def parseContainers(containersJson: String ) : java.util.List[Container] = {

    val containers = new util.ArrayList[Container]()

    val jsContainers = Json.parse(containersJson)

    for ( container <- jsContainers.as[Array[JsValue]] ) {
      containers.add( PartialContainerExtractor.extract(container) )
    }

    containers

  }
}
