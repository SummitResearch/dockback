package dockback.rest.repositories

import dockback.domain.Container
import dockback.domain.docker.DockerPartialContainer
import play.api.libs.json.JsValue

object FullContainerExtractor {

  def extract(jsContainer: JsValue): Container = {

    val dockerContainerId = (jsContainer \ "Id").as[String]
    val networkMode = (jsContainer \ "HostConfig" \ "NetworkMode").as[String]
    val ipAddress = (jsContainer \ "NetworkSettings" \ "Networks" \ "bridge" \ "IPAddress").as[String]
    val status = (jsContainer \ "State" \ "Status").as[String]
    val startedAt = (jsContainer \ "State" \ "StartedAt").as[String]
    val finishedAt = (jsContainer \ "State" \ "FinishedAt").as[String]
    val logPath = (jsContainer \ "LogPath").as[String]
    val pid = (jsContainer \ "State" \ "Pid").as[Long]
//TO
    Container( dockerContainerId = dockerContainerId, networkMode = networkMode, ipAddress = ipAddress, status = status,
      startedAt = startedAt, finishedAt = finishedAt, logPath = logPath, pid = pid )

  }

}
