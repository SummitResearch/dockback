package dockback.rest.controllers

import java.util

import dockback.domain.{DockerImage, Host}
import dockback.dto.{CreateHostRequest, UpdateHostRequest}
import dockback.rest.repositories.HostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation._
import org.springframework.web.client.RestTemplate

@RestController
class HostController @Autowired() ( hostRepository: HostRepository ) {

  @RequestMapping(value = Array("/host"), method = Array(RequestMethod.POST))
  def create(@RequestBody request: CreateHostRequest ) : Host = {

    val newHost = new Host(
      id = null,
      hostname = request.hostname,
      sshUser = request.sshUser,
      sshPassword = request.sshPassword
    )

    hostRepository.insert( newHost )

    newHost

  }

  @RequestMapping(value = Array("/host"), method = Array(RequestMethod.GET))
  def readAll() : java.util.List[Host] = {
    hostRepository.findAll()
  }

  @RequestMapping(value = Array("/host/{id}"), method = Array(RequestMethod.GET))
  def read( @PathVariable("id") id: String ) : Host = {
    hostRepository.findOne( id )
  }

  @RequestMapping(value = Array("/host/{id}"), method = Array(RequestMethod.DELETE))
  def delete( @PathVariable("id") id: String ) = {
    hostRepository.delete( id )
  }

  @RequestMapping(value = Array("/host/{id}"), method = Array(RequestMethod.PUT))
  def update(@PathVariable("id") id: String, @RequestBody request: UpdateHostRequest) : Host = {

    val device = hostRepository.findOne( id )

    val updatedHost = new Host(
      id = device.id,
      hostname = request.hostname,
      sshUser = request.sshUser,
      sshPassword = request.sshPassword
    )

    hostRepository.save( updatedHost )

    updatedHost

  }

  @RequestMapping(value = Array("/host/{id}/images"), method = Array(RequestMethod.GET))
  def readAllImages( @PathVariable("id") id: String ) : String = {
    val host = hostRepository.findOne( id )
    val restTemplate = new RestTemplate()
    return restTemplate.getForObject(s"http://${host.hostname}:2375/images/json", classOf[String])
  }

  @RequestMapping(value = Array("/host/{id}/containers"), method = Array(RequestMethod.GET))
  def readAllContainers( @PathVariable("id") id: String ) : String = {
    val host = hostRepository.findOne( id )
    val restTemplate = new RestTemplate()
    return restTemplate.getForObject(s"http://${host.hostname}:2375/containers/json", classOf[String])
  }


}
