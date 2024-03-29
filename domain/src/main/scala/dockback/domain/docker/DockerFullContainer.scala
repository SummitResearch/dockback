package dockback.domain.docker

case class DockerFullContainer(
  containerId: String,
  created: String,
  path: String,
  args: Array[String],
  state: DockerContainerState,
  image: String,
  resolvConfPath: String,
  hostnamePath: String,
  hostsPath: String,
  logPath: String,
  name: String,
  restartCount: Int,
  driver: String,
  execDriver: String,
  mountLabel: String,
  processLabel: String,
  appArmorProfile: String,
  execIds: Array[String],
  hostConfig: DockerHostConfig,
  graphDriver: DockerGraphDriver,
  mounts: Array[String],
  config: DockerConfig,
  networkSettings: DockerNetworkSettings
)
