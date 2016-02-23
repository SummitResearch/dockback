package dockback.domain

case class BundleInfo(
  epocCreateTime: Long,
  inodeInfo: InodeInfo,
  ownership: String
)