package dockback.metaInfo

import fr.janalyse.ssh.SSH
import com.mongodb.casbah.Imports._

case class ContainerInfo(host: String) {
  var OS: String = ""
  var name: String = ""
  var ID: String = ""
  var lsResult = ""
  var result = "" //new Array[String](50)
  var responseResults = new Array[String](50)
  
  def getInfo(host: String) {
    //jassh.SSH.once(host, "dockback", "changeme")
    SSH.shell("192.168.39.132", "dockback", "changeme") { sh => 
      //lsResult = sh.execute("ls")
      result = sh.execute("echo changeme | sudo -S docker info")
      responseResults = result.split("\\r?\\n")
    }
    //println("lsResult: " + lsResult)
    println("dockerResult: " + result)
    println("responseResults second: " + responseResults(1))
    
    //store container name, OS, ID to mongoDB
    storeContainerInfo(responseResults)
  }
  def storeContainerInfo(responseResults: Array[String]) {
    val mongoClient = MongoClient()
  }
   
}
