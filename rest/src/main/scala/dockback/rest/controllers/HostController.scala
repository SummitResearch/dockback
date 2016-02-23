package dockback.rest.controllers

import java.util

import com.mongodb.casbah.commons.Logger
import dockback.domain.{DockerPartialContainer, DockbackImage, Host}
import dockback.dto.{CreateHostRequest, UpdateHostRequest}
import dockback.rest.repositories._
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DuplicateKeyException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation._
import org.springframework.web.client.{RestClientException, RestTemplate}

import collection.JavaConversions._

@RestController
class HostController @Autowired() ( hostRepository: HostRepository, imageRepository: ImageRepository, containerRepository: ContainerRepository ) {

  val logger = LoggerFactory.getLogger( classOf[HostController])

  @RequestMapping(value = Array("/host"), method = Array(RequestMethod.POST))
  def create(@RequestBody request: CreateHostRequest ) : Host = {

    val newHost = new Host(
      id = null,
      hostname = request.hostname,
      port = request.port,
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
      port = request.port,
      sshUser = request.sshUser,
      sshPassword = request.sshPassword
    )

    hostRepository.insert( updatedHost )

    updatedHost

  }

  def syncImage(image: DockbackImage) = {
    logger.debug( "Syncing image: " + image.toString )

    val oldImage = imageRepository.findByImageId( image.imageId )

    if( oldImage != null ) {
      logger.debug( "Old image: " + oldImage.toString )
      val refreshedImage = DockbackImage( oldImage.id, image.imageId, image.parentId, image.repTags, image.created )
      imageRepository.save( refreshedImage )
    } else {
      logger.debug( "Inserting Image: " + image.toString )
      val insertedImage = imageRepository.insert( image )
      logger.debug( "Inserted Image: " + insertedImage.toString )
    }
  }

  def syncImages(images: util.List[DockbackImage]) = {

    for( image <- images ) {
      syncImage( image )
    }

  }

  @RequestMapping(value = Array("/host/{id}/image"), method = Array(RequestMethod.GET))
  def readAllImages( @PathVariable("id") id: String ) : java.util.List[DockbackImage] = {
    val host = hostRepository.findOne( id )
    val restTemplate = new RestTemplate()

    val images = PartialImageJsonToObjectFactory.parseImages( restTemplate.getForObject(s"http://${host.hostname}:${host.port}/images/json", classOf[String]) )

    syncImages( images )

    return imageRepository.findAll()
  }

  @RequestMapping(value = Array("/host/{hostId}/image/{imageId}"))
  def readImage( @PathVariable("hostId") hostId: String, @PathVariable("imageId") imageId: String ) : DockbackImage = {
    return imageRepository.findOne( imageId )
  }

  def syncContainer(container: DockerPartialContainer) = {
    logger.debug( "Syncing container: " + container.toString )

    val oldContainer = containerRepository.findByContainerId( container.containerId )
    if( oldContainer != null ) {
      logger.debug("Old container: " + oldContainer.toString )
      val refreshedContainer = DockerPartialContainer(oldContainer.id, container.containerId, container.names, container.image, container.imageId, container.created, container.status )
      containerRepository.save( refreshedContainer )
    } else {
      logger.debug( "Inserting container: " + container.toString )
      val insertedContainer = containerRepository.insert( container )
      logger.debug( "Inserted container: " + insertedContainer.toString )
    }
  }

  def syncContainers(containers: util.List[DockerPartialContainer]) = {
    for( container <- containers ) {
      syncContainer( container )
    }
  }

  @RequestMapping(value = Array("/host/{id}/container"), method = Array(RequestMethod.GET))
  def readAllContainers( @PathVariable("id") id: String ) : java.util.List[DockerPartialContainer] = {
    val host = hostRepository.findOne( id )
    val restTemplate = new RestTemplate()

    val containers = PartialContainerJsonToObjectFactory.parseContainers( restTemplate.getForObject(s"http://${host.hostname}:${host.port}/containers/json", classOf[String] ) )

    syncContainers( containers )

    return containerRepository.findAll()
  }

  @RequestMapping(value = Array("/host/{hostId}/container/{containerId}"))
  def readContainer( @PathVariable("hostId") hostId: String, @PathVariable("containerId") containerId: String ) : DockerPartialContainer = {
    val host = hostRepository.findOne( hostId )
    val containerFromMongo = containerRepository.findOne( containerId )
    val restTemplate = new RestTemplate()

    val container = PartialContainerJsonToObjectFactory.parseContainer( restTemplate.getForObject(s"http://${host.hostname}:${host.port}/containers/${containerFromMongo.containerId}/json", classOf[String] ) )
    syncContainer( container )
    return containerRepository.findOne( containerId )
  }

  @ResponseStatus(value = HttpStatus.CONFLICT, reason = "duplicate field")
  @ExceptionHandler(Array(classOf[DuplicateKeyException]))
  def duplicateField() {}

  @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE, reason = "docker host unavailable")
  @ExceptionHandler(Array(classOf[RestClientException]))
  def dockerHostUnavailable() {}

}
