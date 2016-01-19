package dockback.rest.repositories

import java.util

import dockback.domain.Image

import play.api.libs.json._ // JSON library
import play.api.libs.json.Reads._ // Custom validation helpers
import play.api.libs.functional.syntax._ // Combinator syntax

object ImageJsonToObjectFactory {

  def parseImage( imageJson: String ) : Image = {

    val jsImage: JsValue = Json.parse(imageJson)

    extractImage(jsImage)

  }

  private def extractImage(jsImage: JsValue): Image = {
    val imageId = (jsImage \ "Id").as[String]
    val parentId = (jsImage \ "ParentId").as[String]

    val repoTags = (jsImage \ "RepoTags").as[Array[String]]

    val createdTime = (jsImage \ "Created").as[Long]

    Image("", imageId, parentId, repoTags, createdTime)
  }

  def parseImages(imagesJson: String ) : java.util.List[Image] = {

    val images = new util.ArrayList[Image]()

    val jsImages = Json.parse(imagesJson)

    for ( image <- jsImages.as[Array[JsValue]] ) {
      images.add( extractImage(image) )
    }

    images

  }
}
