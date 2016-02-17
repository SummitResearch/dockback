package dockback.rest.repositories

import dockback.domain.DockbackPartialContainer
import org.springframework.data.mongodb.repository.MongoRepository

trait ContainerRepository extends MongoRepository[DockbackPartialContainer, String] {
  def findByContainerId( containerId: String ) : DockbackPartialContainer
}