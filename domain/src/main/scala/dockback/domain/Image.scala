package dockback.domain

import dockback.domain.docker.{DockerPartialImage, DockerFullImage}
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

import scala.annotation.meta.field

@Document
case class Image(
  id: String = null,
  @(Indexed@field)(unique = true) dockerImageId: String = null,
  parentId: String = "",
  repoTags: Array[String] = Array[String](),
  created: Long = 0L,
  createdString: String = "",
  size: Long = 0L,
  virtualSize: Long = 0L,
  architecture: String = "",
  os: String = "",
  dockerVersion: String = "",
  cmd: Array[String] = Array[String]()//,
//  policies: List[Policy] = List[Policy](),
//  checkpoints: List[Checkpoint] = List[Checkpoint]()
)
