package dockback.rest.repositories

import java.util

import dockback.domain.Image
import play.api.libs.json.Reads._
import play.api.libs.json._ // Combinator syntax

object ImageJsonToObjectFactory {

  def parsePartialImage( imageJson: String ) : Image = {

    val jsImage: JsValue = Json.parse(imageJson)

    PartialImageExtractor.extract(jsImage)

  }

  def parseFullImage( imageJson: String ) : Image = {

    val jsImage: JsValue = Json.parse(imageJson)

    FullImageExtractor.extract(jsImage)

  }

  def parsePartialImages(imagesJson: String ) : java.util.List[Image] = {

    val images = new util.ArrayList[Image]()

    val jsImages = Json.parse(imagesJson)

    for ( image <- jsImages.as[Array[JsValue]] ) {
      images.add( PartialImageExtractor.extract(image) )
    }

    images

  }
}
