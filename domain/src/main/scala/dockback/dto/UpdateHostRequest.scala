package dockback.dto

case class UpdateHostRequest( id: String, port: Int, hostname: String, sshUser: String, sshPassword: String )