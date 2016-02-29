package dockback.rest.repositories

import dockback.domain.Image
import play.api.libs.json._ // JSON library
import play.api.libs.json.Reads._ // Custom validation helpers
import play.api.libs.functional.syntax._ // Combinator syntax

object FullImageExtractor {

  implicit val image: Reads[FullImageHolder] = (
    (JsPath \ "Id").read[String] and
    (JsPath \ "Parent").read[String] and
    (JsPath \ "RepoTags").read[Array[String]] and
    (JsPath \ "Created").read[String] and
    (JsPath \ "Size").read[Long] and
    (JsPath \ "VirtualSize").read[Long] and
    (JsPath \ "Architecture").read[String] and
    (JsPath \ "Os").read[String] and
    (JsPath \ "DockerVersion").read[String] and
    (JsPath \ "Config" \ "Cmd").read[Array[String]]
  )(FullImageHolder.apply _)

  def extract(jsImage: JsValue): Image = {

    val fullImage = jsImage.as[FullImageHolder]

    Image( dockerImageId = fullImage.dockerImageId, parentId = fullImage.parentId,
      repoTags = fullImage.repoTags, createdString = fullImage.createdString, size = fullImage.size,
      virtualSize = fullImage.virtualSize, architecture = fullImage.architecture, os = fullImage.os,
      dockerVersion = fullImage.dockerVersion, cmd = fullImage.cmd )

  }

  case class FullImageHolder(
    dockerImageId: String,
    parentId: String,
    repoTags: Array[String],
    createdString: String,
    size: Long,
    virtualSize: Long,
    architecture: String,
    os: String,
    dockerVersion: String,
    cmd: Array[String]
  )
}

