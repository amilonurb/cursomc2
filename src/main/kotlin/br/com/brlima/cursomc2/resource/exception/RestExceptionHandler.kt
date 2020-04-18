package br.com.brlima.cursomc2.resource.exception

import br.com.brlima.cursomc2.service.exception.ObjectNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class RestExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException::class)
    fun handleObjectNotFound(exception: ObjectNotFoundException, request: HttpServletRequest): ResponseEntity<StandardError> {
        val error = StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.reasonPhrase, exception?.message, request.requestURI)
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error)
    }
}
