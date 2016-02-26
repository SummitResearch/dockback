package dockback.domain

import dockback.domain.docker.DockerInfo
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

import scala.annotation.meta.field

@Document
case class Host(
   id: String,
   @(Indexed@field)(unique = true)
   hostname: String,
   port: Int,
   sshUser: String,
   sshPassword: String,
   dockerInfo: DockerInfo
)
