package dockback.rest.service

import dockback.domain.{Container, Host, Checkpoint}
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class CheckpointDispatchService {

  @Async
  def runCheckpoint( host: Host, container: Container, checkpoint: Checkpoint ) : Unit = {
    println( "Do the checkpoint." )


    new dockback.docker.CRProxy.Checkpoint( host.hostname, host.sshUser, host.sshPassword, container.dockerContainerId, null, null, null, true ).exec()

  }

}

