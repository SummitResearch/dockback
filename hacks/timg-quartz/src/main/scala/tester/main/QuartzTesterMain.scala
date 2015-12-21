package tester.main

import org.springframework.boot.SpringApplication
import tester.QuartzTesterConfiguration

object QuartzTesterMain extends App {

  println("Hello World Quartz Tester")

  SpringApplication.run( classOf[QuartzTesterConfiguration] )

}
