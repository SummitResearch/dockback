package dockback.rest.repositories

import dockback.domain.DockbackImage
import play.api.libs.json.JsValue

trait PartailImageExtractor extends Extractor {

  def extract(jsImage: JsValue): DockbackImage = {
    val imageId = (jsImage \ "Id").as[String]
    val parentId = (jsImage \ "ParentId").as[String]

    val repoTags = (jsImage \ "RepoTags").as[Array[String]]

    val createdTime = (jsImage \ "Created").as[Long]

    DockbackImage(null, imageId, null, null, null, repoTags, createdTime, null, null)
  }

}
