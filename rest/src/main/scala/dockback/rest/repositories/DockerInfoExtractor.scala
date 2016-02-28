package dockback.rest.repositories

import dockback.domain.docker.{DockerInfo, DockerRegistryConfig, DockerDriverStatus}
import play.api.libs.json._ // JSON library
import play.api.libs.json.Reads._ // Custom validation helpers
import play.api.libs.functional.syntax._ // Combinator syntax

object DockerInfoExtractor {

  implicit val dockerInfoPart1Reads: Reads[DockerInfoPart1] = (
    (JsPath \ "ID").read[String] and
    (JsPath \ "Containers").read[Int] and
    (JsPath \ "Images").read[Int] and
    (JsPath \ "Driver").read[String] and
//    (JsPath \ "DriverStatus").read[JsArray].map { arr =>
//      val items = arr(0).asOpt[JsArray]
//      val m = items.map(el => Map(el(0).asOpt[String].getOrElse("") -> el(1).asOpt[String].getOrElse("")))
//      m.map(_.filterKeys(_.nonEmpty)).getOrElse(Map.empty)
//    }.orElse(Reads.pure(Map.empty[String, String])) and
    (JsPath \ "MemoryLimit").read[Boolean] and
    (JsPath \ "SwapLimit").read[Boolean] and
    (JsPath \ "CpuCfsPeriod").read[Boolean] and
    (JsPath \ "CpuCfsQuota").read[Boolean] and
    (JsPath \ "IPv4Forwarding").read[Boolean] and
    (JsPath \ "BridgeNfIptables").read[Boolean] and
    (JsPath \ "BridgeNfIp6tables").read[Boolean] and
    (JsPath \ "Debug").read[Boolean] and
    (JsPath \ "NFd").read[Int] and
    (JsPath \ "OomKillDisable").read[Boolean] and
    (JsPath \ "NGoroutines").read[Int] and
    (JsPath \ "SystemTime").read[String]
    ) (DockerInfoPart1.apply _)

  implicit val dockerInfoPart2Reads: Reads[DockerInfoPart2] = (
    (JsPath \ "ExecutionDriver").read[String] and
    (JsPath \ "LoggingDriver").read[String] and
    (JsPath \ "NEventsListener").read[Int] and
    (JsPath \ "KernelVersion").read[String] and
    (JsPath \ "OperatingSystem").read[String] and
    (JsPath \ "IndexServerAddress").read[String] and
    //    (JsPath \ "RegistryConfig").read[DockerRegistryConfig] and
    (JsPath \ "InitSha1").read[String] and
    (JsPath \ "InitPath").read[String] and
    (JsPath \ "NCPU").read[Int] and
    (JsPath \ "MemTotal").read[Long] and
    (JsPath \ "DockerRootDir").read[String] and
    (JsPath \ "HttpProxy").read[String] and
    (JsPath \ "HttpsProxy").read[String] and
    (JsPath \ "NoProxy").read[String] and
    (JsPath \ "Name").read[String] and
    (JsPath \ "Labels").read[Array[String]].orElse(Reads.pure(Array.empty[String])) and
    (JsPath \ "ExperimentalBuild").read[Boolean] and
    (JsPath \ "ServerVersion").read[String] and
    (JsPath \ "ClusterStore").read[String] and
    (JsPath \ "ClusterAdvertise").read[String]) (DockerInfoPart2.apply _)

  def extract(jsInfo: JsValue): DockerInfo = {
    val part1 = jsInfo.as[DockerInfoPart1]
    val part2 = jsInfo.as[DockerInfoPart2]

    DockerInfo(
      part1.instanceId,
      part1.containers,
      part1.images,
      part1.driver,
//      part1.driverStatus,
      part1.memoryLimit,
      part1.swapLimit,
      part1.cpuCfsPeriod,
      part1.cpuCfsQuota,
      part1.ipv4Forwarding,
      part1.bridgeNfIptables,
      part1.bridgeNfIp6tables,
      part1.debug,
      part1.nfd,
      part1.oomKillDisable,
      part1.nGoRoutines,
      part1.systemTime,
      part2.executionDriver,
      part2.loggingDriver,
      part2.nEventsListener,
      part2.kernelVersion,
      part2.operatingSystem,
      part2.indexServerAddress,
//      part2.registryConfig,
      part2.initSha1,
      part2.initPath,
      part2.ncpu,
      part2.memTotal,
      part2.dockerRootDir,
      part2.httpProxy,
      part2.httpsProxy,
      part2.noProxy,
      part2.name,
      part2.labels,
      part2.experimentalBuild,
      part2.serverVersion,
      part2.clusterStore,
      part2.clusterAdvertise
    )
  }

}

case class DockerInfoPart1(
  instanceId: String,
  containers: Int,
  images: Int,
  driver: String,
//  driverStatus: Map[String, String],
  memoryLimit: Boolean,
  swapLimit: Boolean,
  cpuCfsPeriod: Boolean,
  cpuCfsQuota: Boolean,
  ipv4Forwarding: Boolean,
  bridgeNfIptables: Boolean,
  bridgeNfIp6tables: Boolean,
  debug: Boolean,
  nfd: Int,
  oomKillDisable: Boolean,
  nGoRoutines: Int,
  systemTime: String
)

case class DockerInfoPart2(
  executionDriver: String,
  loggingDriver: String,
  nEventsListener: Int,
  kernelVersion: String,
  operatingSystem: String,
  indexServerAddress: String,
//  registryConfig: DockerRegistryConfig,
  initSha1: String,
  initPath: String,
  ncpu: Int,
  memTotal: Long,
  dockerRootDir: String,
  httpProxy: String,
  httpsProxy: String,
  noProxy: String,
  name: String,
  labels:  Array[String],
  experimentalBuild: Boolean,
  serverVersion: String,
  clusterStore: String,
  clusterAdvertise: String
)