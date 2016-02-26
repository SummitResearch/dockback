package dockback.domain

case class DockerRestartPolicy(
  name: String,
  maximumRetryCount: Int
)
