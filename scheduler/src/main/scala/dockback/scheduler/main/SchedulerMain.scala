package dockback.scheduler.main

import dockback.scheduler.SchedulerConfiguration
import org.springframework.boot.SpringApplication

object SchedulerMain extends App {
    SpringApplication.run( classOf[SchedulerConfiguration])
}

