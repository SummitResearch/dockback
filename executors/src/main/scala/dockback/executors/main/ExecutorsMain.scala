package dockback.executors.main

import dockback.executors.ExecutorsConfiguration
import org.springframework.boot.SpringApplication

object ExecutorsMain extends App {
  SpringApplication.run( classOf[ExecutorsConfiguration])
}
