package dockback.rest.repositories

import dockback.domain.DockbackPartialContainer
import play.api.libs.json.JsValue

trait PartialContainerExtractor {

  def extract(jsContainer: JsValue): DockbackPartialContainer = {

    val containerId = (jsContainer \ "Id").as[String]
    val names = (jsContainer \ "Names").as[Array[String]]
    val image = (jsContainer \ "Image").as[String]
    val imageId = (jsContainer \ "ImageID").as[String]
    val created = (jsContainer \ "Created").as[Long]
    val status = (jsContainer \ "Status").as[String]

    DockbackPartialContainer(null, containerId, names, image, imageId, created, status)

  }

}
