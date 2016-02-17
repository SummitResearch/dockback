package dockback.rest.repositories

import java.util

import dockback.domain.DockbackImage
import play.api.libs.json.Reads._
import play.api.libs.json._ // Combinator syntax

object PartialImageJsonToObjectFactory extends PartailImageExtractor {

  def parseImage( imageJson: String ) : DockbackImage = {

    val jsImage: JsValue = Json.parse(imageJson)

    extract(jsImage)

  }

  def parseImages(imagesJson: String ) : java.util.List[DockbackImage] = {

    val images = new util.ArrayList[DockbackImage]()

    val jsImages = Json.parse(imagesJson)

    for ( image <- jsImages.as[Array[JsValue]] ) {
      images.add( extract(image) )
    }

    images

  }
}
