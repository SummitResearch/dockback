package dockback.rest.repositories

import dockback.rest.BaseSpec
import play.api.libs.json.{Json, JsValue}

class DockerInfoExtractorSpec extends BaseSpec {

  info( "As a developer" )
  info( "I want to be able to parse JSON to an info object." )
  info( "Where the info object is a docker info." )

  feature( "Parse an info object from a JSON string" ) {
    scenario("Parse a single info") {
      Given("an input JSON string")

      val infoJson = "\n{\"ID\":\"4UWB:KGTH:NRXG:6VBX:KXBE:7KFV:LTNO:SP4P:I33O:PXNG:5ESR:IQBG\",\"Containers\":7,\"ContainersRunning\":1,\"ContainersPaused\":0,\"ContainersStopped\":6,\"Images\":2,\"Driver\":\"aufs\",\"DriverStatus\":[[\"Root Dir\",\"/var/lib/docker/aufs\"],[\"Backing Filesystem\",\"extfs\"],[\"Dirs\",\"20\"],[\"Dirperm1 Supported\",\"true\"]],\"SystemStatus\":null,\"Plugins\":{\"Volume\":[\"local\"],\"Network\":[\"bridge\",\"null\",\"host\"],\"Authorization\":null},\"MemoryLimit\":true,\"SwapLimit\":false,\"CpuCfsPeriod\":true,\"CpuCfsQuota\":true,\"CPUShares\":true,\"CPUSet\":true,\"IPv4Forwarding\":true,\"BridgeNfIptables\":true,\"BridgeNfIp6tables\":true,\"Debug\":false,\"NFd\":20,\"OomKillDisable\":true,\"NGoroutines\":35,\"SystemTime\":\"2016-02-27T20:50:04.039503558-07:00\",\"ExecutionDriver\":\"native-0.2\",\"LoggingDriver\":\"json-file\",\"NEventsListener\":0,\"KernelVersion\":\"4.2.0-27-generic\",\"OperatingSystem\":\"Ubuntu 15.10\",\"OSType\":\"linux\",\"Architecture\":\"x86_64\",\"IndexServerAddress\":\"https://index.docker.io/v1/\",\"RegistryConfig\":{\"InsecureRegistryCIDRs\":[\"127.0.0.0/8\"],\"IndexConfigs\":{\"docker.io\":{\"Name\":\"docker.io\",\"Mirrors\":null,\"Secure\":true,\"Official\":true}},\"Mirrors\":null},\"InitSha1\":\"7862098ac351bb5959a044a905ece8fb8b58dbd1\",\"InitPath\":\"/usr/lib/docker/dockerinit\",\"NCPU\":1,\"MemTotal\":1041113088,\"DockerRootDir\":\"/var/lib/docker\",\"HttpProxy\":\"\",\"HttpsProxy\":\"\",\"NoProxy\":\"\",\"Name\":\"dockerHost\",\"Labels\":null,\"ExperimentalBuild\":false,\"ServerVersion\":\"1.10.1\",\"ClusterStore\":\"\",\"ClusterAdvertise\":\"\"}"

      val jsInfo: JsValue = Json.parse(infoJson)

      val dockerInfo = DockerInfoExtractor.extract( jsInfo )

      assert(dockerInfo.instanceId.equals("4UWB:KGTH:NRXG:6VBX:KXBE:7KFV:LTNO:SP4P:I33O:PXNG:5ESR:IQBG"))
    }
  }

}
