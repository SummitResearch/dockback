package dockback.docker.proxy

import fr.janalyse.ssh.SSH
import com.mongodb.casbah.Imports._
import dockback.domain.DockbackContainer
import dockback.domain.DockbackContainer

//case class Checkpoint(host: String, imageDir: String, leaveRunning: Boolean, workDir: String, containerId: Long) {
case class Checkpoint(host: String) {
  /*
   * host: is the host to attach to to run a checkpoint
   * imageDir: (Required) is the directory to store checkpoint image files 
   * leaveRunning: boolean to leave the container running after checkpointing, default is false
   * workDir: is the directory for storing log file 
   */
  
  
  var OS: String = ""
  var name: String = ""
  var ID: String = ""
  var lsResult = ""
  var result = "" //new Array[String](50)
  var responseResults = new Array[String](500)
  
  def exec(host: String) {
    //jassh.SSH.once(host, "dockback", "changeme")
//    SSH.shell("192.168.39.132", "dockback", "changeme") { sh => 
    SSH.shell("192.168.39.132", "dockback", "changeme") { sh => 
      //lsResult = sh.execute("ls")
      //runCheckpoint()
      bundleCheckpointContent()
      //gatherCheckpointMetaInfo()
      //persistCheckpointMetaInfo()
      //copyContentBundle()
      //storeContentBundle()
      
      //result = sh.execute("echo changeme | sudo -S docker checkpoint --image-dir=/root/backups/dkbfs/4eeff70a7930 --leave-running=true 4eeff70a7930")
      //responseResults = result.split("\\r?\\n")
    }
    //println("lsResult: " + lsResult)
    println("dockerResult: " + result)
    println("responseResults: " + responseResults.toString())
    
    //store container name, OS, ID to mongoDB
    storeContainerInfo(responseResults)
  }
  def storeContainerInfo(responseResults: Array[String]) {
    val mongoClient = MongoClient()
  }
  
  def runCheckpoint() {
      //var response = ""
      //var responseResults = ""
      SSH.shell("192.168.39.132", "dockback", "changeme") { sh => 
        result = sh.execute("echo changeme | sudo -S docker checkpoint --image-dir=/root/backups/dkbfs/4eeff70a7930 --leave-running=true 4eeff70a7930")
        responseResults = result.split("\\n")
      }
  }
  
  def bundleCheckpointContent() {
      var bundeFileStats = Array.empty[String]
      SSH.shell("192.168.39.132", "dockback", "changeme") { sh => 
        result = sh.execute("echo changeme | sudo -S tar -cvf /root/backups/dkbfs/4eeff70a7930.dkbfs.container.bundle.tar /root/backups/dkbfs/4eeff70a7930 && sudo stat /root/backups/dkbfs/4eeff70a7930.dkbfs.container.bundle.tar")
        bundeFileStats = result.split("\\n")
        result = sh.execute("echo changeme | sudo -S echo second call in ssh")
        publishCheckpointMetaInfo(bundeFileStats)
      }
  }
  
  //def publishCheckpointMetaInfo() {
  def publishCheckpointMetaInfo(bundeFileStats: Array[String]) {
    
    // stat filesystem info for checkpoint image dir

  }
    
  def copyContentBundle() {
    // stat filesystem info for checkpoint image dir
    SSH.shell("192.168.39.132", "dockback", "changeme") { sh => 
      result = sh.execute("echo changeme | sudo -S tar -cvf /root/backups/dkbfs/4eeff70a7930.dkbfs.container.bundle.tar /root/backups/dkbfs/4eeff70a7930")
      responseResults = result.split("\\n")  
    }
    
  }
  
  
   
}