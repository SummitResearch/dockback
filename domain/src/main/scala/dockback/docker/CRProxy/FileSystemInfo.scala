package dockback.docker.CRProxy

case class FileSystemInfo() {
  var containerId: String = _
  var fileName: String = _
  var path: String = _
  var size: String = _
  var accessRights: String = _
  var createDate: String = _
  var lastAccessDate: String = _
  var groupNameOfOwner: String = _
  var groupIdOfOwner: String = _
  var userIdOfOwner: String = _
  var userNameOfOwner: String = _
  var fileSystemType: String = _
  var errorCode: String = _
  var errorMessage: String = _
}