package dockback.rest.repositories

import dockback.domain.Image
import play.api.libs.json.JsValue

trait Extractor {
  def extract(jsImage: JsValue): Image
}
