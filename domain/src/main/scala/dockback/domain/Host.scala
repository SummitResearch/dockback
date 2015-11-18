package dockback.domain

import org.springframework.data.mongodb.core.mapping.Document


@Document
case class Host(
   id: String,
   hostname: String,
   sshUser: String,
   sshPassword: String
)
