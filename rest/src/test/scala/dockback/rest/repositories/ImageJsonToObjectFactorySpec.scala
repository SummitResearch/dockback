package dockback.rest.repositories

import dockback.domain.DockbackImage
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
      val image = PartialImageJsonToObjectFactory.parseImage( imageJson )

      Then("the image should be an image object")

      assert(image.isInstanceOf[DockbackImage])

      And( "the ID should be null")
      assert(null == image.id)

      And( "the image ID should be 'e9ae3c220b23b699cb5e6914af806219b028c78d5cf6fed8eeca98ffaa8c9b43'")
      assert(image.dockerImageId.equals( "e9ae3c220b23b699cb5e6914af806219b028c78d5cf6fed8eeca98ffaa8c9b43" ))

//      And( "the ParentId should be 'a6785352b25c7398637e5ab5a6e989b8371f5dfdf72d9a6cdb00742f262a223e'")
//      assert(image.parentId.equals( "a6785352b25c7398637e5ab5a6e989b8371f5dfdf72d9a6cdb00742f262a223e" ))

      And( "the RepoTags should have one item")
      assert(image.repTags.length == 1)

      And( "the tag 'ubuntu:latest' should be present")
      assert(image.repTags.exists( tag => tag.equals("ubuntu:latest") ))

      And( "the created time should be 1447115707")
      assert(image.created == 1447115707)

    }
    scenario("parse an array of images") {
      Given("an input JSON string with an array of images")

      val imagesJson = """[{"Id":"e9ae3c220b23b699cb5e6914af806219b028c78d5cf6fed8eeca98ffaa8c9b43","ParentId":"a6785352b25c7398637e5ab5a6e989b8371f5dfdf72d9a6cdb00742f262a223e","RepoTags":["ubuntu:latest"],"RepoDigests":[],"Created":1447115707,"Size":0,"VirtualSize":187919300,"Labels":{}},{"Id":"492d45ed288f868325f87bb215e0c6b0ae8656f58bd4b7f2c8ce9b60e9109c06","ParentId":"aee9ec57ca6eb8a1d18c983a1edc4379d00eb3e79081da0262d8e1eed39cc1c9","RepoTags":["dry-run-test:latest"],"RepoDigests":[],"Created":1447019042,"Size":102415164,"VirtualSize":1315569259,"Labels":{}},{"Id":"c51f86c283408d1749d066333f7acd5d33b053b003a61ff6a7b36819ddcbc7b7","ParentId":"039b63dd2cbaa10d6015ea574392530571ed8d7b174090f032211285a71881d0","RepoTags":["busybox:latest"],"RepoDigests":[],"Created":1446330175,"Size":0,"VirtualSize":1108935,"Labels":null},{"Id":"1d073211c498fd5022699b46a936b4e4bdacb04f637ad64d3475f558783f5c3e","ParentId":"5a4526e952f0aa24f3fcc1b6971f7744eb5465d572a48d47c492cb6bbf9cbcda","RepoTags":["ubuntu:14.04"],"RepoDigests":[],"Created":1445551024,"Size":0,"VirtualSize":187914581,"Labels":null},{"Id":"0a6ba66e537a53a5ea94f7c6a99c534c6adb12e3ed09326d4bf3b38f7c3ba4e7","ParentId":"b901d36b6f2fd759c362819c595301ca981622654e7ea8a1aac893fbd2740b4c","RepoTags":["hello-world:latest"],"RepoDigests":[],"Created":1444780068,"Size":0,"VirtualSize":960,"Labels":null}]"""

      When("the string is parsed")
      val images = PartialImageJsonToObjectFactory.parseImages( imagesJson )

      Then( "the result should be a list of image objects" )
      assert(images.isInstanceOf[java.util.List[DockbackImage]])

      And("there should be 5 images")
      assert( images.size() == 5)

      val image = images.get(0)

      And( "the ID should be null")
      assert(null == image.id)

      And( "the image ID should be 'e9ae3c220b23b699cb5e6914af806219b028c78d5cf6fed8eeca98ffaa8c9b43'")
      assert(image.dockerImageId.equals( "e9ae3c220b23b699cb5e6914af806219b028c78d5cf6fed8eeca98ffaa8c9b43" ))
//
//      And( "the ParentId should be 'a6785352b25c7398637e5ab5a6e989b8371f5dfdf72d9a6cdb00742f262a223e'")
//      assert(image.parentId.equals( "a6785352b25c7398637e5ab5a6e989b8371f5dfdf72d9a6cdb00742f262a223e" ))

      And( "the RepoTags should have one item")
      assert(image.repTags.length == 1)

      And( "the tag 'ubuntu:latest' should be present")
      assert(image.repTags.exists( tag => tag.equals("ubuntu:latest") ))

      And( "the created time should be 1447115707")
      assert(image.created == 1447115707)


    }

  }
}
