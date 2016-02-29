package dockback.rest.repositories

import dockback.domain.Container
import org.springframework.data.mongodb.repository.MongoRepository

trait ContainerRepository extends MongoRepository[Container, String] {
  def findByDockerContainerId( dockerContainerId: String ) : Container
}