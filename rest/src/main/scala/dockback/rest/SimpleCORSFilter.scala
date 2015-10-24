package dockback.rest

import javax.servlet._
import javax.servlet.http.HttpServletResponse

import org.springframework.stereotype.Component

@Component
class SimpleCORSFilter extends Filter {

  override def init(filterConfig: FilterConfig): Unit = {}

  override def doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain): Unit = {
    val hsResponse = response.asInstanceOf[HttpServletResponse];
    hsResponse.setHeader("Access-Control-Allow-Origin", "*")
    hsResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE")
    hsResponse.setHeader("Access-Control-Max-Age", "3600")
    hsResponse.setHeader("Access-Control-Allow-Headers", "X-Requested-With")
    chain.doFilter(request, response)
  }

  override def destroy(): Unit = {}

}
