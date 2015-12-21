package tester.main

import org.quartz.JobBuilder._
import org.quartz.{SimpleScheduleBuilder, ScheduleBuilder}
import org.quartz.TriggerBuilder._
import org.quartz.impl.StdSchedulerFactory
import org.springframework.boot.SpringApplication
import tester.QuartzTesterConfiguration

object QuartzTesterMain extends App {

  println("Hello World Quartz Tester")

  SpringApplication.run( classOf[QuartzTesterConfiguration] )

  val scheduler = StdSchedulerFactory.getDefaultScheduler()

  val job = newJob(classOf[HelloJob]).withIdentity("job1", "group1").build()

  val trigger = newTrigger().withIdentity("trigger1", "group1").startNow().withSchedule( SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(40).repeatForever()).build()

  scheduler.scheduleJob( job, trigger )

  scheduler.start()

}
