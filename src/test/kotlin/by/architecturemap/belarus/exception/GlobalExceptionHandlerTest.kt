package by.architecturemap.belarus.exception

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.Import
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ContextConfiguration
import org.springframework.validation.BindException
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException

@ContextConfiguration(classes = [GlobalExceptionHandler::class])
@Import(GlobalExceptionHandler::class)
class GlobalExceptionHandlerTest {

    private val handler = GlobalExceptionHandler()

    @Test
    fun handleIllegalArgumentException_shouldReturnBadRequest() {
        // arrange
        val exception = IllegalArgumentException("Invalid argument")
        val expectedStatus = HttpStatus.BAD_REQUEST
        val expectedBody = "Invalid argument"

        // act
        val actualResponse: ResponseEntity<String> = handler.handleIllegalArgumentException(exception)

        // assert
        assertEquals(expectedStatus, actualResponse.statusCode)
        assertEquals(expectedBody, actualResponse.body)
    }

    @Test
    fun handleNotFoundException_shouldReturnNotFound() {
        // arrange
        val exception = NotFoundException("Resource not found")
        val expectedStatus = HttpStatus.NOT_FOUND
        val expectedBody = "Resource not found"

        // act
        val actualResponse: ResponseEntity<String> = handler.handleNotFoundException(exception)

        // assert
        assertEquals(expectedStatus, actualResponse.statusCode)
        assertEquals(expectedBody, actualResponse.body)
    }

    @Test
    fun handleValidationExceptions_shouldReturnBadRequestWithFieldErrors() {
        // arrange
        val bindingResult = BindException(Any(), "testObject")
        bindingResult.addError(FieldError("testObject", "field1", "Field 1 is invalid"))
        bindingResult.addError(FieldError("testObject", "field2", "Field 2 is invalid"))

        val exception = MethodArgumentNotValidException(null, bindingResult)
        val expectedResponse = mapOf(
            "field1" to "Field 1 is invalid",
            "field2" to "Field 2 is invalid"
        )

        // act
        val actualResponse: Map<String, String> = handler.handleValidationExceptions(exception)

        // assert
        assertEquals(expectedResponse, actualResponse)
    }
}


