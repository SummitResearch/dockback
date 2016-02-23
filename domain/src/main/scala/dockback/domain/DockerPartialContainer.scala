package dockback.domain

case class DockerPartialContainer(
  containerId: String,
  names: Array[String],
  image: String,
  imageId: String,
  created: Long,
  status: String
)
