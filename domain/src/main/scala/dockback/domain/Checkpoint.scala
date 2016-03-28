package dockback.domain

import dockback.docker.CRProxy.FileSystemInfo
import org.springframework.data.mongodb.core.mapping.Document

@Document
case class Checkpoint(
  id: String,
  containerId: String,
  timestamp: Long,
  path: String,
  bundle: Bundle,
  status: CheckpointStatus,
  fileSystemInfo: FileSystemInfo = FileSystemInfo(),
  restored: Boolean = false,
  restoreTime: Long = 0L,
  restoredContainerName: String = "",
  restoredContainerId: String = ""
)
