package dockback.domain

case class Bundle(
  checkpointId: String,
  bundleLocation: String,
  fsStats: BundleStats,
  bundleInfo: BundleInfo
)
