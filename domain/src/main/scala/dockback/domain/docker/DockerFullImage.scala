package dockback.domain.docker

case class DockerFullImage(
  imageId: String,
  repoTags: Array[String],
  repoDigests: Array[String],
  parent: String,
  comment: String,
  created: String,
  container: String,
  containerConfig: DockerConfig,
  dockerVersion: String,
  author: String,
  config: DockerConfig,
  architecture: String,
  os: String,
  size: Long,
  virtualSize: Long,
  graphDriver: DockerGraphDriver
)
