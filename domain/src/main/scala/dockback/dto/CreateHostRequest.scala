package dockback.dto

case class
CreateHostRequest( hostname: String, port: Int, sshUser: String, sshPassword: String )