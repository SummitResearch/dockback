package dockback.domain

case class DockerNetworkSettings(
  bridge: String,
  sandboxId: String,
  hairpinMode: Boolean,
  linkLocalIpv6Address: String,
  linkLocalIpv6PrefixLen: Long,
  ports: Array[Int],
  sandboxKey: String,
  secondaryIpAddresses: Array[String],
  secondaryIpv6Addresses: Array[String],
  endpointId: String,
  gateway: String,
  globalIpv6Address: String,
  globalIpv6PrefixLen: Long,
  ipAddress: String,
  ipPrefixLen: Long,
  ipv6Gateway: String,
  macAddress: String,
  networks: DockerNetworks
)
