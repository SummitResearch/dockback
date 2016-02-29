package dockback.domain.docker

case class DockerLabels(
  `build-date`: Option[String] = None,
  license: Option[String] = None,
  name: Option[String] = None,
  vendor: Option[String] = None
)
