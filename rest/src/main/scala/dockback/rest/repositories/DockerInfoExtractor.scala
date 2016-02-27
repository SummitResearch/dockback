package dockback.rest.repositories

import dockback.domain.docker._
import play.api.libs.json.JsValue

trait DockerInfoExtractor {

  def extract(jsInfo: JsValue): DockerInfo = {
    val id = (jsInfo \ "ID").as[String]
    val containers = (jsInfo \ "Containers").as[Int]
    val images = (jsInfo \ "Images").as[Int]
    val driver = (jsInfo \ "Driver").as[String]

    val jsDriverStatusMap = (jsInfo \ "DriverStatus").as[Map[String, String]]
    val driverStatus = DockerDriverStatus(jsDriverStatusMap)

    val memoryLimit = (jsInfo \ "MemoryLimit").as[Boolean]
    val swapLimit = (jsInfo \ "SwapLimit").as[Boolean]
    val cpuCfsPeriod = (jsInfo \ "CpuCfsPeriod").as[Boolean]
    val cpuCfsQuota = (jsInfo \ "CpuCfsQuota").as[Boolean]
    val ipv4Forwarding = (jsInfo \ "IPv4Forwarding").as[Boolean]
    val bridgeNfIptables = (jsInfo \ "BridgeNfIptables").as[Boolean]
    val bridgeNfIp6tables = (jsInfo \ "BridgeNfIp6tables").as[Boolean]
    val debug = (jsInfo \ "Debug").as[Boolean]
    val nfd = (jsInfo \ "NFd").as[Int]
    val oomKillDisable = (jsInfo \ "OomKillDisable").as[Boolean]
    val nGoRoutines = (jsInfo \ "NGoroutines").as[Int]
    val systemTime = (jsInfo \ "SystemTime").as[String]
    val executionDriver = (jsInfo \ "ExecutionDriver").as[String]
    val loggingDriver = (jsInfo \ "LoggingDriver").as[String]
    val nEventsListener = (jsInfo \ "NEventsListener").as[Int]
    val kernelVersion = (jsInfo \ "KernelVersion").as[String]
    val operatingSystem = (jsInfo \ "OperatingSystem").as[String]
    val indexServerAddress = (jsInfo \ "IndexServerAddress").as[String]

    val insecureRegistryCIDRs = (jsInfo \ "RegistryConfig" \ "InsecureRegistryCIDRs").as[Array[String]]
//    val indexConfigs = (jsInfo \ "RegistryConfig" \ "IndexConfigs").as[Map[String, DockerIndexConfig]]

    val registryConfig = DockerRegistryConfig( insecureRegistryCIDRs, Map("todo" -> DockerIndexConfig("docker.io", null, true, true) ))

    val initSha1 = (jsInfo \ "InitSha1").as[String]
    val initPath = (jsInfo \ "InitPath").as[String]

    val ncpu = (jsInfo \ "NCPU").as[Int]
    val memTotal = (jsInfo \ "MemTotal").as[Long]
    val dockerRootDir = (jsInfo \ "DockerRootDir").as[String]
    val httpProxy = (jsInfo \ "HttpProxy").as[String]
    val httpsProxy = (jsInfo \ "HttpsProxy").as[String]
    val noProxy = (jsInfo \ "NoProxy").as[String]
    val name = (jsInfo \ "Name").as[String]
    val labels = (jsInfo \ "Labels").as[Map[String, String]]
    val experimentalBuild = (jsInfo \ "ExperimentalBuild").as[Boolean]
    val serverVersion = (jsInfo \ "ServerVersion").as[String]
    val clusterStore = (jsInfo \ "ClusterStore").as[String]
    val clusterAdvertise = (jsInfo \ "ClusterAdvertise").as[String]

    DockerInfo(
      id,
      containers,
      images,
      driver,
      driverStatus,
      memoryLimit,
      swapLimit,
      cpuCfsPeriod,
      cpuCfsQuota,
      ipv4Forwarding,
      bridgeNfIptables,
      bridgeNfIp6tables,
      debug,
      nfd,
      oomKillDisable,
      nGoRoutines,
      systemTime,
      executionDriver,
      loggingDriver,
      nEventsListener,
      kernelVersion,
      operatingSystem,
      indexServerAddress,
      registryConfig,
      initSha1: String,
      initPath: String,
      ncpu: Int,
      memTotal: Long,
      dockerRootDir: String,
      httpProxy: String,
      httpsProxy: String,
      noProxy: String,
      name: String,
      labels:  Map[String, String],
      experimentalBuild: Boolean,
      serverVersion: String,
      clusterStore: String,
      clusterAdvertise: String
    )

  }

}
