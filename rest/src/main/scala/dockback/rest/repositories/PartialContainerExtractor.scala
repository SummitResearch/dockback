package dockback.rest.repositories

import dockback.domain.{ContainerType, Container}
import play.api.libs.json.JsValue

trait PartialContainerExtractor {

  def extract(jsContainer: JsValue): Container = {

    val dockerContainerId = (jsContainer \ "Id").as[String]
    val names = (jsContainer \ "Names").as[Array[String]]
    val image = (jsContainer \ "Image").as[String]
    val imageId = (jsContainer \ "ImageID").as[String]
    val created = (jsContainer \ "Created").as[Long]
    val statusMessage = (jsContainer \ "Status").as[String]

    Container( dockerContainerId = dockerContainerId, names = names, image = image, imageId = imageId, created = created, statusMessage = statusMessage )

  }

}
