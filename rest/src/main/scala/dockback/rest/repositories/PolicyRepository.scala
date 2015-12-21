package dockback.rest.repositories

import dockback.domain.Policy
import org.springframework.data.mongodb.repository.MongoRepository

trait PolicyRepository extends MongoRepository[Policy, String]
