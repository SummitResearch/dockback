package dockback.rest.repositories

import dockback.domain.DockerPartialContainer
import play.api.libs.json.JsValue

trait PartialContainerExtractor {

  def extract(jsContainer: JsValue): DockerPartialContainer = {

    val containerId = (jsContainer \ "Id").as[String]
    val names = (jsContainer \ "Names").as[Array[String]]
    val image = (jsContainer \ "Image").as[String]
    val imageId = (jsContainer \ "ImageID").as[String]
    val created = (jsContainer \ "Created").as[Long]
    val status = (jsContainer \ "Status").as[String]
//todo
    DockerPartialContainer(/*null,*/ containerId, names, image, imageId, created, status)

  }

}
