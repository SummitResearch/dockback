package dockback.rest.controllers

import dockback.domain.Policy
import dockback.dto.{CreatePolicyRequest, UpdatePolicyRequest}
import dockback.rest.repositories.PolicyRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DuplicateKeyException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation._

@RestController
class PolicyController @Autowired() ( policyRepository: PolicyRepository ) {

  @RequestMapping(value = Array("/policy"), method = Array(RequestMethod.POST))
  def create(@RequestBody request: CreatePolicyRequest ) : Policy = {

    val newPolicy = new Policy(
      id = null,
      name = request.name,
      description = request.description,
      schedule = request.schedule
    )

    policyRepository.insert( newPolicy )

    newPolicy

  }

  @RequestMapping(value = Array("/policy"), method = Array(RequestMethod.GET))
  def readAll() : java.util.List[Policy] = {
    policyRepository.findAll()
  }

  @RequestMapping(value = Array("/policy/{id}"), method = Array(RequestMethod.GET))
  def read( @PathVariable("id") id: String ) : Policy = {
    policyRepository.findOne( id )
  }

  @RequestMapping(value = Array("/policy/{id}"), method = Array(RequestMethod.DELETE))
  def delete( @PathVariable("id") id: String ) = {
    policyRepository.delete( id )
  }

  @RequestMapping(value = Array("/policy/{id}"), method = Array(RequestMethod.PUT))
  def update(@PathVariable("id") id: String, @RequestBody request: UpdatePolicyRequest) : Policy = {

    val device = policyRepository.findOne( id )

    val updatedPolicy = new Policy(
      id = device.id,
      name = request.name,
      description = request.description,
      schedule = request.schedule
    )

    policyRepository.save( updatedPolicy )

    updatedPolicy

  }

  @ResponseStatus(value = HttpStatus.CONFLICT, reason = "duplicate field")
  @ExceptionHandler(Array(classOf[DuplicateKeyException]))
  def duplicateField() {}

}
