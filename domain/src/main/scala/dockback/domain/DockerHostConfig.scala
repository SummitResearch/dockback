package dockback.domain

case class DockerHostConfig(
  binds: Array[String],
  containerIdFile: String,
  lxcConf: Array[String],
  memory: Long,
  memoryReservation: Long,
  memorySwap: Long,
  kernelMemory: Long,
  cpuShares: Long,
  cpuPeriod: Long,
  cpusetCpus: String,
  cpusetMem: String,
  cpuQuota: Long,
  blkioWeight: Long,
  oomKillDisable: Boolean,
  memorySwappiness: Int,
  privileged: Boolean,
  portBindings: Array[Int],
  links: Array[String],
  publishAllPorts: Boolean,
  dns: Array[String],
  dnsOptions: Array[String],
  dnsSearch: Array[String],
  extraHosts: Array[String]
)