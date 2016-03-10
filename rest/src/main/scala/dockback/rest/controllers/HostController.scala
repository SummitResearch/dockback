package dockback.rest.controllers

import java.util
import java.util.Date

import dockback.domain.docker.{DockerPartialImage, DockerInfo}
import dockback.domain._
import dockback.dto.{CreateHostRequest, UpdateHostRequest}
import dockback.rest.repositories._
import dockback.rest.service.CheckpointDispatchService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DuplicateKeyException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation._
import org.springframework.web.client.{RestClientException, RestTemplate}

import collection.JavaConversions._

@RestController
class HostController @Autowired() ( hostRepository: HostRepository, imageRepository: ImageRepository, containerRepository: ContainerRepository, checkpointDispatchService: CheckpointDispatchService, checkpointRepository: CheckpointRepository ) {

  val logger = LoggerFactory.getLogger( classOf[HostController])

  @RequestMapping(value = Array("/host"), method = Array(RequestMethod.POST))
  def create(@RequestBody request: CreateHostRequest ) : Host = {

    val newHost = new Host(
      id = null,
      hostname = request.hostname,
      port = request.port,
      sshUser = request.sshUser,
      sshPassword = request.sshPassword,
      dockerInfo = null //gatherDockerInfo( request )
    )

    val dockerInfo = gatherDockerInfo( request.hostname, request.port )

    val hostWithInfo = Host( newHost.id, newHost.hostname, newHost.port, newHost.sshUser, newHost.sshPassword, dockerInfo)
    hostRepository.insert( hostWithInfo )

    hostWithInfo

  }

  private def gatherDockerInfo( hostname: String, port: Int ): DockerInfo = {
    val restTemplate = new RestTemplate()
    DockerInfoJsonToObjectFactory.parseInfo( restTemplate.getForObject(s"http://${hostname}:${port}/info", classOf[String] ) )
  }

  @RequestMapping(value = Array("/host"), method = Array(RequestMethod.GET))
  def readAll() : java.util.List[Host] = {
    val foundHosts = hostRepository.findAll()
    for ( oldHost <- foundHosts ) {
      val dockerInfo = gatherDockerInfo( oldHost.hostname, oldHost.port )
      val hostWithInfo = Host( oldHost.id, oldHost.hostname, oldHost.port, oldHost.sshUser, oldHost.sshPassword, dockerInfo)
      hostRepository.save( hostWithInfo )
    }
    hostRepository.findAll()
  }

  @RequestMapping(value = Array("/host/{id}"), method = Array(RequestMethod.GET))
  def read( @PathVariable("id") id: String ) : Host = {
    val oldHost = hostRepository.findOne( id )
    val dockerInfo = gatherDockerInfo( oldHost.hostname, oldHost.port )
    val hostWithInfo = Host( oldHost.id, oldHost.hostname, oldHost.port, oldHost.sshUser, oldHost.sshPassword, dockerInfo)
    hostRepository.save( hostWithInfo )
    hostRepository.findOne( id )
  }

  @RequestMapping(value = Array("/host/{id}/info"), method = Array(RequestMethod.GET))
  def readHostInfoOnly( @PathVariable("id") id: String ) : DockerInfo = {
    val oldHost = hostRepository.findOne( id )
    val dockerInfo = gatherDockerInfo( oldHost.hostname, oldHost.port )
    val hostWithInfo = Host( oldHost.id, oldHost.hostname, oldHost.port, oldHost.sshUser, oldHost.sshPassword, dockerInfo)
    hostRepository.save( hostWithInfo )
    hostRepository.findOne( id )
    gatherDockerInfo( oldHost.hostname, oldHost.port )
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
      sshPassword = request.sshPassword,
      dockerInfo = gatherDockerInfo( request.hostname, request.port )
    )

    hostRepository.save( updatedHost )

    updatedHost

  }

  def syncPartialImage( image: Image ) = {
    logger.info( "Syncing image: " + image.toString )

    val oldImage = imageRepository.findByDockerImageId( image.dockerImageId )

    logger.info("Old Image: " + oldImage )

    if( oldImage != null ) {

      logger.info( "Old image: " + oldImage.toString )

      val refreshedImage = Image( oldImage.id, image.dockerImageId, image.parentId, image.repoTags, image.created,
        image.createdString, image.size, image.virtualSize, image.architecture, image.os, image.dockerVersion,
        image.cmd/*, oldImage.policies, oldImage.checkpoints*/ )

      imageRepository.save( refreshedImage )

    } else {

      logger.info( "Inserting Image: " + image.toString )

      val insertedImage = imageRepository.insert( Image( null,image.dockerImageId, image.parentId, image.repoTags,
        image.created, image.createdString, image.size, image.virtualSize, image.architecture, image.os,
        image.dockerVersion, image.cmd/*, List[Policy](), List[Checkpoint]( )*/ ) )

      logger.info( "Inserted Image: " + insertedImage.toString )

    }
  }

  def syncPartialImages(images: util.List[Image], host: Host) = {

    val restTemplate = new RestTemplate()

    for( image <- images ) {
      val fullImage = ImageJsonToObjectFactory.parseFullImage(
        restTemplate.getForObject(s"http://${host.hostname}:${host.port}/images/${image.dockerImageId}/json",
          classOf[String]) )
      logger.info( fullImage.toString )
      syncPartialImage( image.copy( createdString = fullImage.createdString, architecture = fullImage.architecture,
        os = fullImage.os, dockerVersion = fullImage.dockerVersion, cmd = fullImage.cmd ) )
    }

  }

  @RequestMapping(value = Array("/host/{id}/image"), method = Array(RequestMethod.GET))
  def readAllImages( @PathVariable("id") id: String ) : java.util.List[Image] = {
    val host = hostRepository.findOne( id )
    val restTemplate = new RestTemplate()

    val partialImages = ImageJsonToObjectFactory.parsePartialImages( restTemplate.getForObject(s"http://${host.hostname}:${host.port}/images/json", classOf[String]) )

    syncPartialImages( partialImages, host )

    imageRepository.findAll()
  }

  @RequestMapping(value = Array("/host/{hostId}/image/{imageId}"))
  def readImage( @PathVariable("hostId") hostId: String, @PathVariable("imageId") imageId: String ) : Image = {
    val host = hostRepository.findOne( hostId )
    val restTemplate = new RestTemplate()
    val partialImages = ImageJsonToObjectFactory.parsePartialImages( restTemplate.getForObject(s"http://${host.hostname}:${host.port}/images/json", classOf[String]) )
    syncPartialImages( partialImages, host )
    imageRepository.findOne( imageId )
  }

  @RequestMapping(value = Array("/host/{hostId}/image/{imageId}/full"))
  def readFullImage( @PathVariable("hostId") hostId: String, @PathVariable("imageId") imageId: String ) : String = {
    val host = hostRepository.findOne( hostId )
    val imageFromMongo = imageRepository.findOne( imageId )
    val restTemplate = new RestTemplate()
    restTemplate.getForObject(s"http://${host.hostname}:${host.port}/images/${imageFromMongo.dockerImageId}/json", classOf[String])
  }

  def syncContainer(container: Container) = {
    logger.info( "Syncing container: " + container.toString )

    val oldContainer = containerRepository.findByDockerContainerId( container.dockerContainerId )

    if( oldContainer != null ) {

      logger.info("Old container: " + oldContainer.toString )

      val refreshedContainer = container.copy( id = oldContainer.id, dockerContainerId = container.dockerContainerId,
        names = container.names, image = container.image, imageId = container.imageId, created = container.created,
        statusMessage = container.statusMessage, networkMode = container.networkMode, ipAddress = container.ipAddress,
        status = container.status, pid = container.pid, startedAt = container.startedAt,
        finishedAt = container.finishedAt, logPath = container.logPath, currentHostId = container.currentHostId )

      val savedContainer = containerRepository.save( refreshedContainer )

      logger.info( "Saved container: " + savedContainer.toString )

    } else {

      logger.info( "Inserting container: " + container.toString )

      val insertedContainer = containerRepository.insert( Container( null,
        dockerContainerId = container.dockerContainerId, names = container.names, image = container.image,
        imageId = container.imageId, created = container.created, statusMessage = container.statusMessage,
        networkMode = container.networkMode, ipAddress = container.ipAddress, status = container.status,
        pid = container.pid, startedAt = container.startedAt, finishedAt = container.finishedAt,
        logPath = container.logPath, currentHostId = container.currentHostId) )

      logger.info( "Inserted container: " + insertedContainer.toString )

    }
  }

  def syncContainers(containers: util.List[Container], host: Host) = {

    val restTemplate = new RestTemplate()

    for( container <- containers ) {
      val fullContainer = ContainerJsonToObjectFactory.parseFullContainer(
        restTemplate.getForObject(s"http://${host.hostname}:${host.port}/containers/${container.dockerContainerId}/json",
          classOf[String]) )
      logger.info( "Full Container: " + fullContainer.toString )
      syncContainer( container.copy( networkMode = fullContainer.networkMode, ipAddress = fullContainer.ipAddress,
        status = fullContainer.status, startedAt = fullContainer.startedAt, finishedAt = fullContainer.finishedAt,
        logPath = fullContainer.logPath, pid = fullContainer.pid, currentHostId = host.id ) )
    }
  }

  @RequestMapping(value = Array("/host/{id}/container"), method = Array(RequestMethod.GET))
  def readAllContainers( @PathVariable("id") id: String ) : java.util.List[Container] = {
    val host = hostRepository.findOne( id )
    val restTemplate = new RestTemplate()

    val containers = ContainerJsonToObjectFactory.parseContainers( restTemplate.getForObject(s"http://${host.hostname}:${host.port}/containers/json", classOf[String] ) )
    syncContainers( containers, host )

    containerRepository.findAll()
  }

  @RequestMapping(value = Array("/host/{hostId}/container/{containerId}"))
  def readContainer( @PathVariable("hostId") hostId: String, @PathVariable("containerId") containerId: String ) : Container = {
    val host = hostRepository.findOne( hostId )
    val containerFromMongo = containerRepository.findOne( containerId )
    val restTemplate = new RestTemplate()

    val containers = ContainerJsonToObjectFactory.parseContainers( restTemplate.getForObject(s"http://${host.hostname}:${host.port}/containers/json", classOf[String] ) )
    syncContainers( containers, host )

    containerRepository.findOne( containerId )
  }

  @RequestMapping(value = Array("/host/{hostId}/container/{containerId}/full"))
  def readFullContainer( @PathVariable("hostId") hostId: String, @PathVariable("containerId") containerId: String ) : String = {
    val host = hostRepository.findOne( hostId )
    val containerFromMongo = containerRepository.findOne( containerId )
    val restTemplate = new RestTemplate()

    restTemplate.getForObject(s"http://${host.hostname}:${host.port}/containers/${containerFromMongo.dockerContainerId}/json", classOf[String] )

  }

  @RequestMapping(value = Array("/host/{hostId}/container/{containerId}/checkpoint"), method = Array(RequestMethod.POST))
  def checkPointContainer( @PathVariable("hostId") hostId: String, @PathVariable("containerId") containerId: String ) : Checkpoint = {
    val host = hostRepository.findOne( hostId )
    val containerFromMongo = containerRepository.findOne( containerId )
    val restTemplate = new RestTemplate()

    //todo save checkpoint to repository

    val checkpoint = Checkpoint(null, "", new Date().getTime, "", Bundle(null, "", BundleStats(new Date().getTime, InodeInfo(), ""), BundleInfo(new Date().getTime, InodeInfo(), "")), CheckpointStatus.PENDING)

    checkpointRepository.insert(checkpoint)

    checkpointDispatchService.runCheckpoint( host, containerFromMongo, checkpoint )

    checkpoint

  }

  @ResponseStatus(value = HttpStatus.CONFLICT, reason = "duplicate field")
  @ExceptionHandler(Array(classOf[DuplicateKeyException]))
  def duplicateField() {}

  @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE, reason = "docker host unavailable")
  @ExceptionHandler(Array(classOf[RestClientException]))
  def dockerHostUnavailable() {}

}
