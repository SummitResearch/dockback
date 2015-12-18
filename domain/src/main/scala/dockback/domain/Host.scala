package dockback.domain

import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

import scala.annotation.meta.field

@Document
case class Host(
   id: String,
   @(Indexed@field)(unique = true)
   hostname: String,
   sshUser: String,
   sshPassword: String
)
