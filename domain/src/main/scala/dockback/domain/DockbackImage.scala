package dockback.domain

import dockback.domain.docker.{DockerPartialImage, DockerFullImage}
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

import scala.annotation.meta.field

@Document
case class DockbackImage(
  id: String = null,
  @(Indexed@field)(unique = true) dockerImageId: String,
  dockerContainerId: String,
  dockerFullImage: DockerFullImage,
  dockerPartialImage: DockerPartialImage,
  repTags: Array[String],
  created: Long,
  policies: List[Policy],
  checkpoints: List[Checkpoint]
)
