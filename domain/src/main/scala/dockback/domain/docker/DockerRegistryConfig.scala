package dockback.domain.docker

case class DockerRegistryConfig(
  insecureRegistryCidrs: Array[String],
  indexConfigs: Map[String, DockerIndexConfig]
)
