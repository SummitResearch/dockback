package dockback.domain

import org.springframework.data.mongodb.core.mapping.Document

@Document
case class Checkpoint(
  id: String,
  checkpointId: String,
  timestamp: Long,
  path: String,
  bundle: Bundle,
  status: CheckpointStatus
)
