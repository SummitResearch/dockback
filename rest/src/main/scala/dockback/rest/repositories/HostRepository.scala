package dockback.rest.repositories

import dockback.domain.Host
import org.springframework.data.mongodb.repository.MongoRepository

trait HostRepository extends MongoRepository[Host, String]