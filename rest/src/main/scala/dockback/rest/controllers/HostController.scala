package dockback.rest.controllers

import dockback.rest.repositories.HostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RestController

@RestController
class HostController @Autowired() ( hostRepository: HostRepository ) {


}
