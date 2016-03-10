package dockback.docker.CRProxy

import fr.janalyse.ssh.SSH
import com.mongodb.casbah.Imports._
import dockback.domain.Container
import dockback.domain.Container

class Checkpoint(host: String, user: String, password: String, containerId: String, imageDir: String, bundleRepoDir: String, workDir: String, leaveRunning: Boolean) {
  /*
   * host: (Required) is the host to attach to to run a checkpoint
   * user: (Optional)
   * password: (Optional)
   * containerId: (Required) of the container to checkpoint
   * TODO: change to allow a list of containers to be restored. 
   * imageDir: (Optional) is the directory to store checkpoint image files
   * dbkBundleRepoDir (Optional) is the dockback repo directory where image bundles will be stores as a .tar file 
   * workDir: (Optional) is the directory for storing log file 
   * leaveRunning: (Optional) boolean to leave the container running after checkpointing, default is false for docker
   * 	note: this option is not known to be working in this version of the docker, in all cases it appears the 
   * 	checkpointed container is stopped.
   * 
   * return the filesysteminfo object from the exec, and write the bundle to the local file system (structured fs)
   * 
   */
  
  var defaultUser: String = "dockback"
  var defaultPassword: String = "changeme"
  var defaultImageDir: String = "/root/backups/dkbfs/"
  var defaultBundleRepoDar: String = "/dockback/bundleRepo/"
  var defaultLeaveRunning: Boolean = true
  var defaultCpBundleSuffix: String = ".dkbfs.container.bundle.tar"
  
  var cpBundleRepoDir: String = _
  var cpUser: String = _
  var cpPassword: String = _
  var cpImageDir: String = _
  var cpLeaveRunning: Boolean = _
  
  var result = "" //new Array[String](50)
  var responseResults = new Array[String](500)
  
  def exec() : FileSystemInfo = {
    // scrub parms for use of default values if not defined
    if (user == null) {cpUser = defaultUser} else cpUser = user
    if (password == null) {cpPassword = defaultPassword} else cpPassword = password
    if (imageDir == null) {cpImageDir = defaultImageDir} else cpImageDir = imageDir
    if (bundleRepoDir == null) {cpBundleRepoDir = defaultBundleRepoDar} else cpBundleRepoDir = bundleRepoDir
    if (leaveRunning == null) {cpLeaveRunning = defaultLeaveRunning} else cpLeaveRunning = leaveRunning
    
    runCheckpoint()
    bundleAndRecordCheckpointContent()
    copyContentBundle()
    
    println("dockerResult: " + result)
    println("responseResults: " + responseResults.toString())
    
    //store container name, OS, ID to mongoDB
    storeContainerInfo(responseResults)
  }
  
  def storeContainerInfo(responseResults: Array[String]) {
    // may have TimG persist the results, not doing it here. 
    //val mongoClient = MongoClient()
  }

  def runCheckpoint() {
      var checkpointCommand = s"echo $cpPassword | sudo -S docker checkpoint --image-dir=$cpImageDir$containerId --leave-running=$cpLeaveRunning $containerId"
      SSH.shell(host, cpUser, cpPassword) { sh => 
        result = sh.execute(checkpointCommand)
        //TODO: check for errors and return an error code if this fails.
        // example errors: "no such id" | "cannot checkpoint container...CRIU version must be 1.5.2 or higher" 
        responseResults = result.split("\\n")
      }
  }

  def bundleAndRecordCheckpointContent() {
      var bundeFileStats = ""
      var tarImageBundleCommand = s"echo $cpPassword | sudo -S tar -cvf $cpImageDir$containerId$defaultCpBundleSuffix $cpImageDir$containerId"
      SSH.shell(host, cpUser, cpPassword) { sh => 
        result = sh.execute(tarImageBundleCommand)
        //TODO: check for errors like:  
        // now stat the filesystem for information that will be persisted about this bundle
        var formattedStatInfo = "--format \"name:%n accessRights:%A createDate:%W lastAccessDate:%X groupName:%G groupId:%g userIdOwner:%u userNameOwner:%U  fileSysType:%T\""
        var statInfoCommand = s"echo $cpPassword | sudo -S stat $formattedStatInfo $cpImageDir$containerId$defaultCpBundleSuffix"
        result = sh.execute(statInfoCommand)
        //result = sh.execute("echo $cpPassword | sudo -S tar -cvf /root/backups/dkbfs/4eeff70a7930.dkbfs.container.bundle.tar /root/backups/dkbfs/4eeff70a7930 && sudo stat /root/backups/dkbfs/4eeff70a7930.dkbfs.container.bundle.tar")
        bundeFileStats = result
        saveCheckpointFileSystemInfo(bundeFileStats)
      }
  }
  
  def saveCheckpointFileSystemInfo(bundeFileStats: String) {
    // split the stat string result into a map
    // then instance FileSystemInfo and populate based on field values
    var test: String = "name:/root/backups/dkbfs/4eeff70a7930.dkbfs.container.bundle.tar accessRights:-rw-r--r-- createDate:0 lastAccessDate:1457064676 groupName:root groupId:0 userIdOwner:0 userNameOwner:root  fileSysType:0"
    // there is an error in the scala shell command package that is not returning the proper result, hard coding to test.
    val result = test.split(" ").map(_ split ":") collect { case Array(k,v) => (k,v)} toMap
    var fileSysInfo = new FileSystemInfo()
    fileSysInfo.fileName = result("name")
    fileSysInfo.accessRights = result("accessRights")
    fileSysInfo.createDate = result("createDate")
    fileSysInfo.lastAccessDate = result("lastAccessDate")
    fileSysInfo.groupNameOfOwner = result("groupName")
    fileSysInfo.groupIdOfOwner = result("groupId")
    fileSysInfo.userIdOfOwner = result("userIdOwner")
    fileSysInfo.userNameOfOwner = result("userNameOwner")
    fileSysInfo.fileSystemType = result("fileSysType")

    return fileSysInfo
  }
  
  def publishCheckpointMetaInfo(bundeFileStats: Array[String]) {
    // do nothing here for now. Assume docker CRProxy is called and just returns data to be persisted elsewhere.

  }

  def copyContentBundle() {
    // stat filesystem info for checkpoint image dir
    SSH.shell(host, cpUser, cpPassword) { sh => 
      result = sh.execute("echo $cpPassword | sudo -S cp $cpImageDir+$containerId+$defaultCpBundleSuffix $cpBundleRepoDir/.")
      //responseResults = result.split("\\n")  
    }
    
  }
  
}