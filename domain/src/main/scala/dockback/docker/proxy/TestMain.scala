package dockback.docker.proxy

object TestMain {
  
  def main(args: Array[String]): Unit = {
    println("metaInfo main test output")
    var host = "192.168.39.132"
    val checkpoint = new Checkpoint(host)
    checkpoint.exec(host)
  }
  
}