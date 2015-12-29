package dockback.domain

import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

import scala.annotation.meta.field

@Document
case class Container ( id: String, @(Indexed@field)(unique = true) containerId: String,  names: Array[String], image: String, imageId: String, created: Long, status: String )