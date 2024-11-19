package by.architecturemap.belarus.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(ex: IllegalArgumentException): ResponseEntity<String> =
        ResponseEntity(ex.message ?: "Invalid input", HttpStatus.BAD_REQUEST)

    @ExceptionHandler(IllegalStateException::class)
    fun handleIllegalStateException(ex: IllegalStateException): ResponseEntity<String> =
        ResponseEntity(ex.message ?: "Invalid state variable", HttpStatus.BAD_REQUEST)

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(ex: NotFoundException): ResponseEntity<String> =
        ResponseEntity(ex.message ?: "Not found", HttpStatus.NOT_FOUND)

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): Map<String, String> =
        mutableMapOf<String, String>()
            .apply {
                ex.bindingResult.allErrors.forEach { error ->
                    val fieldName = (error as FieldError).field
                    val errorMessage = error.defaultMessage
                    this[fieldName] = errorMessage ?: "Error message not available"
                }
            }
}
