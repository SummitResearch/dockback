package dockback.rest.main

import dockback.rest.RestConfiguration
import org.springframework.boot.SpringApplication

object RestMain extends App {
  SpringApplication.run( classOf[RestConfiguration] )
}
