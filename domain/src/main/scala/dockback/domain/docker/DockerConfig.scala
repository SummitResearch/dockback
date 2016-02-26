package dockback.domain.docker

import dockback.domain.DockerSignal

case class DockerConfig(
  hostname: String,
  domainname: String,
  user: String,
  attachStdin: Boolean,
  attachStdout: Boolean,
  attachStderr: Boolean,
  tty: Boolean,
  openStdin: Boolean,
  stdinOnce: Boolean,
  env: String,
  cmd: Array[String],
  image: String,
  volumes: Array[String],
  workingDir: String,
  entrypoint: String,
  onBuild: String,
  labels: DockerLabels,
  stopsignal: DockerSignal
)

