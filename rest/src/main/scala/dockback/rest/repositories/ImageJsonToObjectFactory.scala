package dockback.rest.repositories

import java.util

import dockback.domain.Image

import play.api.libs.json._ // JSON library
import play.api.libs.json.Reads._ // Custom validation helpers
import play.api.libs.functional.syntax._ // Combinator syntax

object ImageJsonToObjectFactory {

  def parseImage( imageJson: String ) : Image = {

    val json: JsValue = Json.parse(imageJson)

    val imageId = (json \ "Id").as[String]
    val parentId = (json \ "ParentId").as[String]

    val repoTags = (json \ "RepoTags" ).as[Array[String]]

    val createdTime = (json \ "Created").as[Long]

    Image("", imageId, parentId, repoTags, createdTime)

  }

  def parseImages( imagesJson: String ) : java.util.List[Image] = {

    new util.ArrayList[Image]()

  }
}
