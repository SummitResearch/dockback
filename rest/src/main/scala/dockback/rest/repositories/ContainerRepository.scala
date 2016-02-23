package dockback.rest.repositories

import dockback.domain.DockerPartialContainer
import org.springframework.data.mongodb.repository.MongoRepository

trait ContainerRepository extends MongoRepository[DockerPartialContainer, String] {
  def findByContainerId( containerId: String ) : DockerPartialContainer
}