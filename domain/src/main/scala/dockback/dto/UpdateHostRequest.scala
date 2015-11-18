package dockback.dto

case class UpdateHostRequest( id: String, hostname: String, sshUser: String, sshPassword: String )