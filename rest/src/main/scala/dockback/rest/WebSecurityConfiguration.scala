package dockback.rest

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.{EnableWebSecurity, WebSecurityConfigurerAdapter}

@EnableWebSecurity
@Configuration
class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  override def configure(http: HttpSecurity): Unit = {
    http.authorizeRequests.anyRequest.fullyAuthenticated.and.asInstanceOf[HttpSecurity].httpBasic.and.csrf.disable
  }

}
