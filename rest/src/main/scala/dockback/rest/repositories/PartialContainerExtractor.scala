package dockback.rest.repositories

import dockback.domain.{ContainerType, DockbackContainer}
import play.api.libs.json.JsValue

trait PartialContainerExtractor {

  def extract(jsContainer: JsValue): DockbackContainer = {

    val dockerContainerId = (jsContainer \ "Id").as[String]
    val names = (jsContainer \ "Names").as[Array[String]]
    val image = (jsContainer \ "Image").as[String]
    val imageId = (jsContainer \ "ImageID").as[String]
    val created = (jsContainer \ "Created").as[Long]
    val status = (jsContainer \ "Status").as[String]
//todo
    DockbackContainer(null, imageId, dockerContainerId, null, null, ContainerType.DOCKER, "localhost", null, null)

  }

}
