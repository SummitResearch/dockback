package dockback.docker.CRProxy

object TestMain {
  
  def main(args: Array[String]): Unit = {
    //"192.168.39.132", "dockback", "changeme"
    println("metaInfo main test output")
    var host = "192.168.39.130"
    var user = "dockback"
    var password = "changeme"
    var containerId = "4eeff70a7930"
    var imageDir = ""
    var bundleRepoDir = ""
    var leaveRunning = true
    
    val checkpoint = new Checkpoint(host, user, password, containerId, null, null, null , leaveRunning)    
    checkpoint.exec()
    
  }
  
}