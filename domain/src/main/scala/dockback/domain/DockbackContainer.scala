package dockback.domain

import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

import scala.annotation.meta.field

@Document
case class DockbackContainer(
  id: String = null,
  imageId: String,
  @(Indexed@field)(unique = true) dockerContainerId: String,
  dockerFullContainer: DockerFullContainer,
  dockerPartialContainer: DockerPartialContainer,
  containerType: ContainerType,
  currentHostId: String,
  policies: List[Policy],
  checkpoints: List[Checkpoint]
)

