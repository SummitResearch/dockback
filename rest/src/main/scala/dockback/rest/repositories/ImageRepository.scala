package dockback.rest.repositories

import dockback.domain.Image
import org.springframework.data.mongodb.repository.MongoRepository

trait ImageRepository extends MongoRepository[Image, String] {
  def findByDockerImageId( dockerImageId: String ) : Image
}