package dockback.domain.docker

case class DockerIndexConfig(
  name: String,
  mirrors: Array[String],
  secure: Boolean,
  official: Boolean
)
