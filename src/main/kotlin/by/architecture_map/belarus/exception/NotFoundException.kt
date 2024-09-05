package by.architecture_map.belarus.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Value Not Found.")
class NotFoundException(message: String) : RuntimeException()