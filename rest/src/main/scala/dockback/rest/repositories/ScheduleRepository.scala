package dockback.rest.repositories

import dockback.domain.Schedule
import org.springframework.data.mongodb.repository.MongoRepository

trait ScheduleRepository extends MongoRepository[Schedule, String]
