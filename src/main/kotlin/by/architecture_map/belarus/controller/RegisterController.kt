package by.architecture_map.belarus.controller

import by.architecture_map.belarus.dto.RegistrationUserDTO
import by.architecture_map.belarus.dto.UserDTO
import by.architecture_map.belarus.service.UserService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/register")
@CrossOrigin(origins = ["http://localhost:7200", "http://localhost:4200", "*"])
class RegisterController(private val userService: UserService) {

    @Operation(summary = "Registration")
    @PostMapping
    fun register(@RequestBody registrationUserDto: RegistrationUserDTO): ResponseEntity<UserDTO> =
        ResponseEntity(userService.register(registrationUserDto), HttpStatus.CREATED)

    @Operation(summary = "Confirm email")
    @GetMapping("/confirm-email")
    fun confirmEmail(@RequestParam("token") token: String): ResponseEntity<String> =
        userService.confirmEmail(token)
            .let { ResponseEntity("Email confirmed successfully", HttpStatus.OK) }
}
