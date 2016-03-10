package dockback.docker.CRProxy

import fr.janalyse.ssh.SSH

class Restore(host: String, user: String, password: String, containerId: String, imageDir: String, workDir: String, force: Boolean, allowShell: Boolean) {
  /*
   * host: (Required) is the host to attach to to run a checkpoint
   * user: (Optional)
   * password: (Optional)
   * containerId: (Required) of the container to checkpoint
   * TODO: change to allow a list of containers to be restored. 
   * imageDir: (Optional) is the directory to store checkpoint image files
   * workDir: (Optional) is the directory for storing log file 
   * force: (Optional) bypass checks for current container state. default to true
   * allowShell: (Optional) allow restoring shell jobs. default to true
   * 
   */
  
  
  var defaultUser: String = "dockback"
  var defaultPassword: String = "changeme"
  var defaultImageDir: String = "/root/backups/dkbfs/"
  var defaultWorkDir: String = "/root/backups/dkbfs/workDir"
  var defaultForce: Boolean = true
  var defaultAllowShell: Boolean = true
  var defaultCpBundleSuffix = "chk"

  var crUser: String = _
  var crPassword: String = _
  var crImageDir: String = _
  var crForce: Boolean = _
  var crAllowShell: Boolean = _
  
  var result = ""
  var responseResults = new Array[String](500)
  
  def exec() {
    // scrub parms for use of default values if not defined
    if (user == null) {crUser = defaultUser} else crUser = user
    if (password == null) {crPassword = defaultPassword} else crPassword = password
    if (imageDir == null) {crImageDir = defaultImageDir} else crImageDir = imageDir
    if (force == null) {crForce = defaultForce} else crForce = force
    if (allowShell == null) {crAllowShell = defaultAllowShell} else crAllowShell = allowShell
    
    getBundleAndExtractCheckpointContent()
    runRestore()
    
    println("dockerResult: " + result)
    println("responseResults: " + responseResults.toString())
    
    //store container name, OS, ID to mongoDB
  }

  def saveCheckpointFileSystemInfo(bundeFileStats: String): Unit = ???

  def getBundleAndExtractCheckpointContent() {
      var bundeFileStats = ""
      var tarImageExtractCommand = s"echo $crPassword | sudo -S tar -xvf $crImageDir$containerId$defaultCpBundleSuffix $crImageDir$containerId"
      SSH.shell(host, crUser, crPassword) { sh =>
        result = sh.execute(tarImageExtractCommand)
        //TODO: check for errors like:  
        // now stat the filesystem for information that will be persisted about this bundle
        var formattedStatInfo = "--format \"name:%n accessRights:%A createDate:%W lastAccessDate:%X groupName:%G groupId:%g userIdOwner:%u userNameOwner:%U  fileSysType:%T\""
        var statInfoCommand = s"echo $crPassword | sudo -S stat $formattedStatInfo $crImageDir$containerId$defaultCpBundleSuffix"
        result = sh.execute(statInfoCommand)
        //result = sh.execute("echo $cpPassword | sudo -S tar -cvf /root/backups/dkbfs/4eeff70a7930.dkbfs.container.bundle.tar /root/backups/dkbfs/4eeff70a7930 && sudo stat /root/backups/dkbfs/4eeff70a7930.dkbfs.container.bundle.tar")
        bundeFileStats = result
        saveCheckpointFileSystemInfo(bundeFileStats)
      }
  }
   
  def runRestore() {
      var restoreCommand = s"echo $crPassword | sudo -S docker restore --image-dir=$crImageDir$containerId --allow-shell=$crAllowShell --force=$crForce $containerId"
      SSH.shell(host, crUser, crPassword) { sh =>
        result = sh.execute(restoreCommand)
        //TODO: check for errors and return an error code if this fails.
        // example errors: "no such id" | "cannot restore container...CRIU version must be 1.5.2 or higher" 
        responseResults = result.split("\\n")
      }
  }

      
  
}
