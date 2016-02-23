package dockback.domain

import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

import scala.annotation.meta.field

@Document
case class DockbackImage(
  id: String = null,
  @(Indexed@field)(unique = true) imageId: String,
  parentId: String,
  repTags: Array[String],
  created: Long
)
