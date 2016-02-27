package dockback.rest.repositories

import dockback.domain.DockbackContainer
import org.springframework.data.mongodb.repository.MongoRepository

trait ContainerRepository extends MongoRepository[DockbackContainer, String] {
  def findByDockerContainerId( dockerContainerId: String ) : DockbackContainer
}