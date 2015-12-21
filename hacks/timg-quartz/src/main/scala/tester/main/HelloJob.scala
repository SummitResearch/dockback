package tester.main

import org.quartz.{JobExecutionContext, Job}

class HelloJob extends Job {
  override def execute(context: JobExecutionContext): Unit = {
    println("Hello from Hello Job")
  }
}
