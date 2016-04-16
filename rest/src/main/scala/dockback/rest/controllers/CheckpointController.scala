package dockback.rest.controllers

import java.util
import java.util.Date

import dockback.domain._
import dockback.domain.docker.DockerInfo
import dockback.dto.{CreateHostRequest, CreateRestoreRequest, UpdateHostRequest}
import dockback.rest.repositories._
import dockback.rest.service.CheckpointDispatchService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DuplicateKeyException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation._
import org.springframework.web.client.{RestClientException, RestTemplate}

import scala.collection.JavaConversions._

@RestController
class CheckpointController @Autowired()(hostRepository: HostRepository, imageRepository: ImageRepository, containerRepository: ContainerRepository, checkpointDispatchService: CheckpointDispatchService, checkpointRepository: CheckpointRepository ) {

  val logger = LoggerFactory.getLogger( classOf[CheckpointController])

  @RequestMapping(value = Array("/checkpoint"), method = Array(RequestMethod.GET))
  def readAllCheckpoints() : java.util.List[Checkpoint] = {
    checkpointRepository.findAll()
  }

  @RequestMapping(value = Array("/checkpoint/{checkpointId}"), method = Array(RequestMethod.GET))
  def readCheckpoint( @PathVariable("checkpointId") checkpointId: String) : Checkpoint = {
    checkpointRepository.findOne(checkpointId)
  }

  @RequestMapping(value = Array("/checkpoint/{checkpointId}/restore"), method = Array(RequestMethod.POST))
  def restoreContainer( @PathVariable("checkpointId") checkpointId: String, @RequestBody request: CreateRestoreRequest ) : Checkpoint = {
    val host = hostRepository.findOne( request.hostId )
    val containerFromMongo = containerRepository.findOne( request.containerId )
    val checkpoint = checkpointRepository.findOne(checkpointId)
    checkpointDispatchService.restore( host, containerFromMongo, checkpoint )
    checkpoint
  }

  @ResponseStatus(value = HttpStatus.CONFLICT, reason = "duplicate field")
  @ExceptionHandler(Array(classOf[DuplicateKeyException]))
  def duplicateField() {}

}
