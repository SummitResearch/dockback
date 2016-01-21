package dockback.rest.repositories

import dockback.domain.Image
import play.api.libs.json.JsValue

trait ImageExtractor extends Extractor {

  def extract(jsImage: JsValue): Image = {
    val imageId = (jsImage \ "Id").as[String]
    val parentId = (jsImage \ "ParentId").as[String]

    val repoTags = (jsImage \ "RepoTags").as[Array[String]]

    val createdTime = (jsImage \ "Created").as[Long]

    Image("", imageId, parentId, repoTags, createdTime)
  }

}
