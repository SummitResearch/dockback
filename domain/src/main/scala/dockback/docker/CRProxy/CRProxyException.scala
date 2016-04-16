package dockback.docker.CRProxy

class CRProxyException (message: String, cause: Throwable) 
  extends RuntimeException(message) {
    if (cause != null)
      initCause(cause)

    def this(message: String) = this(message, null)  
}
