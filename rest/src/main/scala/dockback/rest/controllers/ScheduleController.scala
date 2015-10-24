package dockback.rest.controllers

import dockback.domain.Schedule
import dockback.rest.repositories.ScheduleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, RestController}

@RestController
class ScheduleController @Autowired() ( scheduleRepository: ScheduleRepository ) {

  @RequestMapping(value = Array("/schedule"), method = Array(RequestMethod.GET))
  def readAll() : java.util.List[Schedule] = {
    scheduleRepository.findAll()
  }

}
