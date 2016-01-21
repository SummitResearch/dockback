package dockback.metaInfo

object TestMain {
  
  def main(args: Array[String]): Unit = {
    println("metaInfo main test output")
    var host = "192.168.39.132"
    val cInfo = new ContainerInfo(host)
    cInfo.getInfo(host)
  }
  
}