package dockback.docker.proxy

class BundleInfo {
    var fileSystemInfo: FileSystemInfo = _
    var bundleType: String = "tar"
    var createdBy: String = "docker checkpoint"   
    var localLocationPath: String = ""
    // TODO: define what DockbackRepository is
    // var localRepositoryInfo: DockbackRepository
}