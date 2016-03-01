package dockback.domain

import dockback.domain.docker.{DockerPartialContainer, DockerFullContainer}
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

import scala.annotation.meta.field

@Document
case class Container(
  id: String = null,
  @(Indexed@field)(unique = true) dockerContainerId: String = "",
  names: Array[String] = Array[String](),
  image: String = "",
  imageId: String = "",
  created: Long = 0L,
  statusMessage: String = "",
  networkMode: String = "",
  ipAddress: String = "",
  status: String = "",
  pid: Long = 0L,
  startedAt: String = "",
  finishedAt: String = "",
  logPath: String = "",
  currentHostId: String = ""
  //  policies: List[Policy] = List[Policy](),
  //  checkpoints: List[Checkpoint] = List[Checkpoint]()
)

