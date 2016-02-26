package dockback.rest.repositories

import dockback.domain.DockbackImage
import dockback.domain.docker.DockerPartialContainer
import dockback.rest.BaseSpec

class ContainerJsonToObjectFactorySpec extends BaseSpec {

  info( "As a developer" )
  info( "I want to be able to parse JSON to an container object or objects." )
  info( "Where the container object is a docker container or an array of docker containers." )

  feature( "Parse a one or more containers from a JSON string" ) {
    scenario("Parse a single container") {
      Given("an input JSON string")

      val containerJson = """{"Id":"32a4a80c2b99a61ed7e44b86116af02c1c1fe6ff8ab4c2662d0edd05b6322d0f","Names":["/some-nginx"],"Image":"nginx","ImageID":"407195ab8b072ce8b237f664b8d032704e8047b8e64139cc2e017163f7161647","Command":"nginx -g 'daemon off;'","Created":1453269534,"Ports":[{"PrivatePort":443,"Type":"tcp"},{"PrivatePort":80,"Type":"tcp"}],"Labels":{},"Status":"Up 3 seconds","HostConfig":{"NetworkMode":"default"}}"""

      When("the string is parsed")
      val container = PartialContainerJsonToObjectFactory.parseContainer( containerJson )

      Then("the container should be an container object")

      assert(container.isInstanceOf[DockerPartialContainer])
//todo
      And( "the ID should be null")
//      assert(null == container.id)

      And( "the image ID should be '407195ab8b072ce8b237f664b8d032704e8047b8e64139cc2e017163f7161647'")
      assert(container.imageId.equals( "407195ab8b072ce8b237f664b8d032704e8047b8e64139cc2e017163f7161647" ))

    }
    scenario("parse an array of containers") {
      Given("an input JSON string with an array of containers")

      val containersJson = """[{"Id":"32a4a80c2b99a61ed7e44b86116af02c1c1fe6ff8ab4c2662d0edd05b6322d0f","Names":["/some-nginx"],"Image":"nginx","ImageID":"407195ab8b072ce8b237f664b8d032704e8047b8e64139cc2e017163f7161647","Command":"nginx -g 'daemon off;'","Created":1453269534,"Ports":[{"PrivatePort":443,"Type":"tcp"},{"PrivatePort":80,"Type":"tcp"}],"Labels":{},"Status":"Up 3 seconds","HostConfig":{"NetworkMode":"default"}},{"Id":"c426b6d8d6f36c7c5919ec8cf9a66a08e72855fdfc64bd5c037faafe24bb552f","Names":["/amazing_snyder"],"Image":"centos","ImageID":"c8a648134623c453dc62abcd747eafa40af057e28cd5937baeebe2ed4c32094e","Command":"/bin/bash","Created":1453269425,"Ports":[],"Labels":{"build-date":"2015-12-23","license":"GPLv2","name":"CentOS Base Image","vendor":"CentOS"},"Status":"Up About a minute","HostConfig":{"NetworkMode":"default"}},{"Id":"fd1d7c7d27bd8f24aba0754c6f3f437dbf9bb2631b67051c5b6c0859e7fb0c15","Names":["/prickly_meitner"],"Image":"ubuntu","ImageID":"e9ae3c220b23b699cb5e6914af806219b028c78d5cf6fed8eeca98ffaa8c9b43","Command":"/bin/bash","Created":1453269288,"Ports":[],"Labels":{},"Status":"Up 4 minutes","HostConfig":{"NetworkMode":"default"}}]"""

      When("the string is parsed")
      val containers = PartialContainerJsonToObjectFactory.parseContainers( containersJson )

      Then( "the result should be a list of image objects" )
      assert(containers.isInstanceOf[java.util.List[DockerPartialContainer]])

      And("there should be 5 images")
      assert( containers.size() == 3)

      val container = containers.get(0)

      //todo
      And( "the ID should be null")
//      assert(null == container.id)

      And( "the image ID should be '407195ab8b072ce8b237f664b8d032704e8047b8e64139cc2e017163f7161647'")
      assert(container.imageId.equals( "407195ab8b072ce8b237f664b8d032704e8047b8e64139cc2e017163f7161647" ))

    }

  }
}
