package dockback.rest

import dockback.helpers.ScalaObjectMapper
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.{Primary, Bean}

@SpringBootApplication
class RestConfiguration {

  @Bean
  @Primary
  def scalaObjectMapper() = new ScalaObjectMapper

}
