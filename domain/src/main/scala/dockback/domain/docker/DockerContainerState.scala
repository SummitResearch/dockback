package dockback.domain.docker

import dockback.domain.ContainerState

case class DockerContainerState(
  status: ContainerState,
  running: Boolean,
  paused: Boolean,
  restarting: Boolean,
  oomKilled: Boolean,
  pid: Int,
  exitCode: Int,
  error: String,
  startedAt: String,
  finishedAt: String
)
