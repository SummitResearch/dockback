package dockback.rest.repositories

import dockback.domain.Checkpoint
import org.springframework.data.mongodb.repository.MongoRepository

trait CheckpointRepository extends MongoRepository[Checkpoint, String]
