package dockback.rest.repositories

import dockback.domain.DockbackImage
import org.springframework.data.mongodb.repository.MongoRepository

trait ImageRepository extends MongoRepository[DockbackImage, String] {
  def findByImageId( imageId: String ) : DockbackImage
}