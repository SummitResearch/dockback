package dockback.rest.repositories

import dockback.domain.Image
import dockback.domain.docker.DockerPartialImage
import dockback.rest.BaseSpec

class ImageJsonToObjectFactorySpec extends BaseSpec {

  info( "As a developer" )
  info( "I want to be able to parse JSON to an partial image object or objects." )
  info( "Where the partial image object is a docker partial image or an array of docker partial images." )

  feature( "Parse a one or more images from a JSON string" ) {
    scenario("Parse a single image") {
      Given("an input JSON string")

      val imageJson = """{"Id":"sha256:14b59d36bae04ba5e4c2bb3ff2906c16d6beac3b6c3ea15273f7458bb5687a3d","ParentId":"a6785352b25c7398637e5ab5a6e989b8371f5dfdf72d9a6cdb00742f262a223e","RepoTags":["ubuntu:latest"],"RepoDigests":null,"Created":1455725598,"Size":187950223,"VirtualSize":187950223,"Labels":{}}"""

      When("the string is parsed")
      val partialImage = ImageJsonToObjectFactory.parsePartialImage( imageJson )

      Then("the image should be an image object")

//      assert(partialImage.isInstanceOf[DockerPartialImage])

      And( "the image ID should be 'sha256:14b59d36bae04ba5e4c2bb3ff2906c16d6beac3b6c3ea15273f7458bb5687a3d'")
      assert(partialImage.dockerImageId.equals( "sha256:14b59d36bae04ba5e4c2bb3ff2906c16d6beac3b6c3ea15273f7458bb5687a3d" ))

      And( "the ParentId should be 'a6785352b25c7398637e5ab5a6e989b8371f5dfdf72d9a6cdb00742f262a223e'")
      assert(partialImage.parentId.equals( "a6785352b25c7398637e5ab5a6e989b8371f5dfdf72d9a6cdb00742f262a223e" ))

      And( "the RepoTags should have one item")
      assert(partialImage.repoTags.length == 1)

      And( "the tag 'ubuntu:latest' should be present")
      assert(partialImage.repoTags.exists( tag => tag.equals("ubuntu:latest") ))

      And( "the created time should be 1447115707")
      assert(partialImage.created == 1455725598)

    }
    scenario("parse an array of images") {
      Given("an input JSON string with an array of images")

      val imagesJson = """[{"Id":"sha256:14b59d36bae04ba5e4c2bb3ff2906c16d6beac3b6c3ea15273f7458bb5687a3d","ParentId":"a6785352b25c7398637e5ab5a6e989b8371f5dfdf72d9a6cdb00742f262a223e","RepoTags":["ubuntu:latest"],"RepoDigests":null,"Created":1455725598,"Size":187950223,"VirtualSize":187950223,"Labels":{}},{"Id":"sha256:690ed74de00f99a7d00a98a5ad855ac4febd66412be132438f9b8dbd300a937d","ParentId":"","RepoTags":["hello-world:latest"],"RepoDigests":null,"Created":1444780068,"Size":960,"VirtualSize":960,"Labels":null}]"""

      When("the string is parsed")
      val partialImages = ImageJsonToObjectFactory.parsePartialImages( imagesJson )

      Then( "the result should be a list of partial image objects" )
//      assert(partialImages.isInstanceOf[java.util.List[DockerPartialImage]])

      And("there should be 2 images")
      assert( partialImages.size() == 2)

      val partialImage = partialImages.get(0)

      And( "the image ID should be 'sha256:14b59d36bae04ba5e4c2bb3ff2906c16d6beac3b6c3ea15273f7458bb5687a3d'")
      assert(partialImage.dockerImageId.equals( "sha256:14b59d36bae04ba5e4c2bb3ff2906c16d6beac3b6c3ea15273f7458bb5687a3d" ))

      And( "the ParentId should be 'a6785352b25c7398637e5ab5a6e989b8371f5dfdf72d9a6cdb00742f262a223e'")
      assert(partialImage.parentId.equals( "a6785352b25c7398637e5ab5a6e989b8371f5dfdf72d9a6cdb00742f262a223e" ))

      And( "the RepoTags should have one item")
      assert(partialImage.repoTags.length == 1)

      And( "the tag 'ubuntu:latest' should be present")
      assert(partialImage.repoTags.exists( tag => tag.equals("ubuntu:latest") ))

      And( "the created time should be 1447115707")
      assert(partialImage.created == 1455725598)


    }

  }
}
