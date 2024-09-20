package by.architecture_map.belarus.controller

import by.architecture_map.belarus.entity.User
import by.architecture_map.belarus.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
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

    @PostMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    fun create(@RequestBody user: User): ResponseEntity<User> =
        ResponseEntity(userService.create(user), HttpStatus.CREATED)

    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    fun findAll(): List<User> = userService.findAll()

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    fun find(@PathVariable id: Int): User = userService.find(id)

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    fun update(@PathVariable id: Int, @RequestBody updatedUser: User): ResponseEntity<User> =
        ResponseEntity(userService.update(id, updatedUser), HttpStatus.NO_CONTENT)

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    fun patchUpdate(@PathVariable id: Int, @RequestBody user: User): ResponseEntity<User> =
        ResponseEntity(userService.patchUpdate(id, user), HttpStatus.NO_CONTENT)

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    fun delete(@PathVariable id: Int): ResponseEntity<String> =
        userService.delete(id).let { ResponseEntity(HttpStatus.NO_CONTENT) }
}