package dockback.rest.repositories

import dockback.domain.Image
import dockback.domain.docker.DockerPartialImage
import dockback.rest.BaseSpec

class ImageJsonToObjectFactorySpec extends BaseSpec {

  info( "As a developer" )
  info( "I want to be able to parse JSON to an partial image object or objects." )
  info( "Where the partial image object is a docker partial image or an array of docker partial images." )

  feature( "Parse a one or more images from a JSON string" ) {
    scenario("Parse a single partial image") {
      Given("an input JSON string")

      val imageJson = """{"Id":"sha256:14b59d36bae04ba5e4c2bb3ff2906c16d6beac3b6c3ea15273f7458bb5687a3d","ParentId":"a6785352b25c7398637e5ab5a6e989b8371f5dfdf72d9a6cdb00742f262a223e","RepoTags":["ubuntu:latest"],"RepoDigests":null,"Created":1455725598,"Size":187950223,"VirtualSize":187950223,"Labels":{}}"""

      When("the string is parsed")
      val partialImage = ImageJsonToObjectFactory.parsePartialImage( imageJson )

      Then("the image should be an image object")

      assert(partialImage.isInstanceOf[Image])

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
      Given("an input JSON string with an array of partial images")

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

  scenario("Parse a single full image") {
    Given("an input JSON string")

    val imageJson = """{"Id":"1a094f2972dee06d601aaa14aa8407117753e99f8e89ed2596e7fd5552385fed","RepoTags":["ubuntu:latest"],"RepoDigests":[],"Parent":"2027caa301755bb89c81edbc92e373886a4576f4f2ce7f23d6ec903ce494c436","Comment":"","Created":"2016-03-18T18:24:28.818918568Z","Container":"85c4705d113ecf7f3ae704f1b7597c5b6bc919fb8ac83e3fa5987ab49cc8153e","ContainerConfig":{"Hostname":"f5ee59d47428","Domainname":"","User":"","AttachStdin":false,"AttachStdout":false,"AttachStderr":false,"Tty":false,"OpenStdin":false,"StdinOnce":false,"Env":[],"Cmd":["/bin/sh","-c","#(nop) CMD [\"/bin/bash\"]"],"Image":"d28d8a6a946d1a1b25a6f4b438d1e92858a17bc58e15c5945d3ae12753a2883d","Volumes":null,"WorkingDir":"","Entrypoint":null,"OnBuild":null,"Labels":{}},"DockerVersion":"1.9.1","Author":"","Config":{"Hostname":"f5ee59d47428","Domainname":"","User":"","AttachStdin":false,"AttachStdout":false,"AttachStderr":false,"Tty":false,"OpenStdin":false,"StdinOnce":false,"Env":[],"Cmd":["/bin/bash"],"Image":"d28d8a6a946d1a1b25a6f4b438d1e92858a17bc58e15c5945d3ae12753a2883d","Volumes":null,"WorkingDir":"","Entrypoint":null,"OnBuild":null,"Labels":{}},"Architecture":"amd64","Os":"linux","Size":0,"VirtualSize":187965448,"GraphDriver":{"Name":"aufs","Data":null}}"""

    When("the string is parsed")
    val fullImage = ImageJsonToObjectFactory.parseFullImage( imageJson )

    Then("the image should be an image object")

    assert(fullImage.isInstanceOf[Image])

  }

  scenario("Parse a single full image with null Cmd") {
    Given("an input JSON string with a null Cmd")

    val imageJson = """{"Id":"492d45ed288f868325f87bb215e0c6b0ae8656f58bd4b7f2c8ce9b60e9109c06","RepoTags":["dry-run-test:latest"],"RepoDigests":[],"Parent":"aee9ec57ca6eb8a1d18c983a1edc4379d00eb3e79081da0262d8e1eed39cc1c9","Comment":"","Created":"2015-11-08T21:44:02.978211399Z","Container":"12a6796ea24bae14c41a7185299f995f52727d94622c1723bac006d6301d8b43","ContainerConfig":{"Hostname":"f813a028846a","Domainname":"","User":"","AttachStdin":false,"AttachStdout":false,"AttachStderr":false,"Tty":false,"OpenStdin":false,"StdinOnce":false,"Env":["PATH=/go/bin:/usr/local/go/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin","LXC_VERSION=1.1.2","GO_VERSION=1.5.1","GOPATH=/go:/go/src/github.com/docker/docker/vendor","DOCKER_CROSSPLATFORMS=linux/386 linux/arm \tdarwin/amd64 darwin/386 \tfreebsd/amd64 freebsd/386 freebsd/arm \twindows/amd64 windows/386","GOARM=5","GO_TOOLS_COMMIT=069d2f3bcb68257b627205f0486d6cc69a231ff9","GO_LINT_COMMIT=f42f5c1c440621302702cb0741e9d2ca547ae80f","REGISTRY_COMMIT=ec87e9b6971d831f0eff752ddb54fb64693e51cd","NOTARY_COMMIT=8e8122eb5528f621afcd4e2854c47302f17392f7","DOCKER_PY_COMMIT=47ab89ec2bd3bddf1221b856ffbaff333edeabb4","DOCKER_BUILDTAGS=apparmor selinux","TOMLV_COMMIT=9baf8a8a9f2ed20a8e54160840c492f937eeaf9a","RSRC_COMMIT=e48dbf1b7fc464a9e85fcec450dddf80816b76e0"],"Cmd":["/bin/sh","-c","#(nop) COPY dir:62cb5ff7f5f2eaf0128e1ec073703755a9a4ee8010f4ff92c1d4b3c312945daa in /go/src/github.com/docker/docker"],"Image":"aee9ec57ca6eb8a1d18c983a1edc4379d00eb3e79081da0262d8e1eed39cc1c9","Volumes":{"/var/lib/docker":{}},"WorkingDir":"/go/src/github.com/docker/docker","Entrypoint":["hack/dind"],"OnBuild":[],"Labels":{}},"DockerVersion":"1.9.0","Author":"Tianon Gravi \u003cadmwiggin@gmail.com\u003e (@tianon)","Config":{"Hostname":"f813a028846a","Domainname":"","User":"","AttachStdin":false,"AttachStdout":false,"AttachStderr":false,"Tty":false,"OpenStdin":false,"StdinOnce":false,"Env":["PATH=/go/bin:/usr/local/go/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin","LXC_VERSION=1.1.2","GO_VERSION=1.5.1","GOPATH=/go:/go/src/github.com/docker/docker/vendor","DOCKER_CROSSPLATFORMS=linux/386 linux/arm \tdarwin/amd64 darwin/386 \tfreebsd/amd64 freebsd/386 freebsd/arm \twindows/amd64 windows/386","GOARM=5","GO_TOOLS_COMMIT=069d2f3bcb68257b627205f0486d6cc69a231ff9","GO_LINT_COMMIT=f42f5c1c440621302702cb0741e9d2ca547ae80f","REGISTRY_COMMIT=ec87e9b6971d831f0eff752ddb54fb64693e51cd","NOTARY_COMMIT=8e8122eb5528f621afcd4e2854c47302f17392f7","DOCKER_PY_COMMIT=47ab89ec2bd3bddf1221b856ffbaff333edeabb4","DOCKER_BUILDTAGS=apparmor selinux","TOMLV_COMMIT=9baf8a8a9f2ed20a8e54160840c492f937eeaf9a","RSRC_COMMIT=e48dbf1b7fc464a9e85fcec450dddf80816b76e0"],"Cmd":null,"Image":"aee9ec57ca6eb8a1d18c983a1edc4379d00eb3e79081da0262d8e1eed39cc1c9","Volumes":{"/var/lib/docker":{}},"WorkingDir":"/go/src/github.com/docker/docker","Entrypoint":["hack/dind"],"OnBuild":[],"Labels":{}},"Architecture":"amd64","Os":"linux","Size":102415164,"VirtualSize":1315569259,"GraphDriver":{"Name":"aufs","Data":null}}"""

    When("the string is parsed")
    val fullImage = ImageJsonToObjectFactory.parseFullImage( imageJson )

    Then("the image should be an image object")

    assert(fullImage.isInstanceOf[Image])

  }

}
