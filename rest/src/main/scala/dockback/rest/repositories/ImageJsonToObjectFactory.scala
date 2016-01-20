package dockback.rest.repositories

import java.util

import dockback.domain.Image

import play.api.libs.json._ // JSON library
import play.api.libs.json.Reads._ // Custom validation helpers
import play.api.libs.functional.syntax._ // Combinator syntax

object ImageJsonToObjectFactory extends ImageExtractor {

  def parseImage( imageJson: String ) : Image = {

    val jsImage: JsValue = Json.parse(imageJson)

    extract(jsImage)

  }

  def parseImages(imagesJson: String ) : java.util.List[Image] = {

    val images = new util.ArrayList[Image]()

    val jsImages = Json.parse(imagesJson)

    for ( image <- jsImages.as[Array[JsValue]] ) {
      images.add( extract(image) )
    }

    images

  }
}
