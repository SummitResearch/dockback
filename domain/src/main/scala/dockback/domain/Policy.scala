package dockback.domain

import org.springframework.data.mongodb.core.mapping.Document

@Document
case class Policy ( id: String, name: String, description: String, schedule: String )