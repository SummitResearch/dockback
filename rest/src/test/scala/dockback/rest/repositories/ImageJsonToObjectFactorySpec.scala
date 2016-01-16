package dockback.rest.repositories

import dockback.domain.Image
import dockback.rest.BaseSpec

class ImageJsonToObjectFactorySpec extends BaseSpec {

  info( "As a developer" )
  info( "I want to be able to parse JSON to an image object or objects." )
  info( "Where the image object is a docker image or an array of docker images." )

  feature( "Parse a one or more images from a JSON string" ) {
    scenario("Parse a single image") {
      Given("an input JSON string")

      val imageJson = """{"Id":"e9ae3c220b23b699cb5e6914af806219b028c78d5cf6fed8eeca98ffaa8c9b43","ParentId":"a6785352b25c7398637e5ab5a6e989b8371f5dfdf72d9a6cdb00742f262a223e","RepoTags":["ubuntu:latest"],"RepoDigests":[],"Created":1447115707,"Size":0,"VirtualSize":187919300,"Labels":{}}"""

      When("the string is parsed")
      val image = ImageJsonToObjectFactory.parseImage( imageJson )

      Then("the image should be an image object")

      assert(image.isInstanceOf[Image])

      And( "the ID should be empty")
      assert(image.id.equals( "" ))

      And( "the image ID should be 'e9ae3c220b23b699cb5e6914af806219b028c78d5cf6fed8eeca98ffaa8c9b43'")
      assert(image.imageId.equals( "e9ae3c220b23b699cb5e6914af806219b028c78d5cf6fed8eeca98ffaa8c9b43" ))

      And( "the ParentId should be 'a6785352b25c7398637e5ab5a6e989b8371f5dfdf72d9a6cdb00742f262a223e'")
      assert(image.parentId.equals( "a6785352b25c7398637e5ab5a6e989b8371f5dfdf72d9a6cdb00742f262a223e" ))

      And( "the RepoTags should have one item")
      assert(image.repTags.length == 1)

      And( "the tag 'ubuntu:latest' should be present")
      assert(image.repTags.exists( tag => tag.equals("ubuntu:latest") ))

      And( "the created time should be 1447115707")
      assert(image.created == 1447115707)

    }
  }
}