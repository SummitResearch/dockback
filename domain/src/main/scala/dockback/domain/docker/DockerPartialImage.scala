package dockback.domain.docker

case class DockerPartialImage(
  imageId: String,
  parentId: String,
  repoTags: Array[String],
  repoDigests: Array[String],
  created: Long,
  size: Long,
  virtualSize: Long//,
//  labels: DockerLabels
)
