package dockback.docker.CRProxy

object TestMain {
  
  def main(args: Array[String]): Unit = {
    //"192.168.39.132", "dockback", "changeme"
    println("metaInfo main test output")
    var host = "192.168.39.130"
    var user = "dockback"
    var password = "changeme"
    var containerId = "d1c16c787a8d"
    var imageDir = ""
    var bundleRepoDir = ""
    var leaveRunning = true
    var test = new DockbackProperties()
    var properties = test.getProperties
    println(properties)
    val checkpoint = new Checkpoint(host, user, password, containerId, null, null, null , leaveRunning)    
    checkpoint.exec()
    
  }
  
}