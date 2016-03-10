package dockback.rest.service

import dockback.domain.Checkpoint
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class CheckpointDispatchService {

  @Async
  def runCheckpoint( checkpoint: Checkpoint ) : Boolean = {
    println( "Do the checkpoint." )

//    new dockback.docker.CRProxy.Checkpoint().exec()

    true

  }

}

