package dockback.domain.docker

case class DockerPartialContainer(
  containerId: String,
  names: Array[String],
  image: String,
  imageId: String,
  created: Long,
  status: String
)
