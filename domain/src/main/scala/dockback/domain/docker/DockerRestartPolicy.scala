package dockback.domain.docker

case class DockerRestartPolicy(
  name: String,
  maximumRetryCount: Int
)
