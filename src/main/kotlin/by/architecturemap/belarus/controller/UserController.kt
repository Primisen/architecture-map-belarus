package by.architecturemap.belarus.controller

import by.architecturemap.belarus.dto.UpdatePasswordDTO
import by.architecturemap.belarus.dto.UserDTO
import by.architecturemap.belarus.entity.User
import by.architecturemap.belarus.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = ["http://localhost:7200", "http://localhost:4200", "*"])
class UserController(
    private val userService: UserService
) {
    @GetMapping()
    fun findAll(): ResponseEntity<List<UserDTO>> =
        ResponseEntity(userService.findAll(), HttpStatus.OK)

    @GetMapping("/{id}")
    fun find(@PathVariable id: Int): ResponseEntity<User> =
        ResponseEntity(userService.find(id), HttpStatus.OK)

    @GetMapping("/username/{username}")
    fun find(@PathVariable username: String): ResponseEntity<User> =
        ResponseEntity(userService.find(username), HttpStatus.OK)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody updatedUser: UserDTO): ResponseEntity<UserDTO> =
        ResponseEntity(userService.update(id, updatedUser), HttpStatus.NO_CONTENT)

    @PutMapping("/update-password")
    fun updatePassword(@RequestBody updatePasswordDTO: UpdatePasswordDTO): ResponseEntity<String> =
        userService.updatePassword(updatePasswordDTO).let { ResponseEntity(HttpStatus.NO_CONTENT) }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<String> =
        userService.delete(id).let { ResponseEntity(HttpStatus.NO_CONTENT) }
}
