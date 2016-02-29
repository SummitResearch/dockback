package dockback.rest.repositories

import dockback.domain.Image

// JSON library
import play.api.libs.json.Reads._
import play.api.libs.json._ // Combinator syntax

object PartialImageExtractor {

  def extract(jsImage: JsValue): Image = {

    val id = (jsImage \ "Id").as[String]
    val parentId = (jsImage \ "ParentId").as[String]
    val repoTags = (jsImage \ "RepoTags").as[Array[String]]
    val created = (jsImage \ "Created").as[Long]
    val size = (jsImage \ "Size").as[Long]
    val virtualSize = (jsImage \ "VirtualSize").as[Long]

    Image( dockerImageId = id, parentId = parentId, repoTags = repoTags, created = created, size = size,
      virtualSize = virtualSize )

  }

}

