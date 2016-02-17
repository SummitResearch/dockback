package dockback.rest.repositories

import dockback.domain.DockbackImage
import play.api.libs.json.JsValue

trait Extractor {
  def extract(jsImage: JsValue): DockbackImage
}
