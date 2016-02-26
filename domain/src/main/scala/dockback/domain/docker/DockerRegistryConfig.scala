package dockback.domain.docker

class DockerRegistryConfig(
  insecureRegistryCidrs: Array[String],
  indexConfigs: Map[String, DockerIndexConfig]
)
