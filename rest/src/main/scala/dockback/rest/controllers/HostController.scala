package dockback.rest.controllers

import dockback.domain.Host
import dockback.dto.{UpdateHostRequest, CreateHostRequest}
import dockback.rest.repositories.HostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation._

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

}
