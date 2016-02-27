package dockback.domain.docker

case class DockerDriverStatus(
    statuses: Map[String, String]
)
