package dockback.rest.service

import dockback.domain
import dockback.domain._
import dockback.rest.repositories.CheckpointRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class CheckpointDispatchService  @Autowired() ( checkpointRepository: CheckpointRepository ) {

  @Async
  def restore(host: Host, container: Container, checkpoint: Checkpoint) : Unit = {

    val result = new dockback.docker.CRProxy.Checkpoint( host.hostname, host.sshUser, host.sshPassword, container.dockerContainerId, null, null, null, true ).exec()

    val updatedCheckpoint = checkpoint.copy( restored = true, restoreTime = System.currentTimeMillis(), restoredContainerName = "restored_container", restoredContainerId = "some_id" )
    checkpointRepository.save( updatedCheckpoint )

  }

  @Async
  def runCheckpoint( host: Host, container: Container, checkpoint: Checkpoint ) : Unit = {

    val result = new dockback.docker.CRProxy.Checkpoint( host.hostname, host.sshUser, host.sshPassword, container.dockerContainerId, null, null, null, true ).exec()

    val newStatus = if ( result.errorCode.equals("0") ) { CheckpointStatus.COMPLETED } else { CheckpointStatus.FAILED }

    val currentBundle = Bundle(
      checkpointId = checkpoint.id,
      bundleLocation = result.path,
      fsStats = BundleStats(
        epocCreateTime = System.currentTimeMillis(),
        inodeInfo = InodeInfo(),
        ownership = result.groupNameOfOwner),
      bundleInfo = BundleInfo(
        epocCreateTime = System.currentTimeMillis(),
        inodeInfo = InodeInfo(),
        ownership = result.groupNameOfOwner))

    val updatedCheckpoint = checkpoint.copy( path = result.path, timestamp = System.currentTimeMillis(), bundle = currentBundle, fileSystemInfo = result, status = newStatus )

    checkpointRepository.save( updatedCheckpoint )

  }

}

