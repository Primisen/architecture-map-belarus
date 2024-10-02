package by.architecturemap.belarus.controller

import by.architecturemap.belarus.entity.Architect
import by.architecturemap.belarus.service.ArchitectService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/architects")
@CrossOrigin(origins = ["http://localhost:7200", "http://localhost:4200", "*"])
class ArchitectController(
    private val architectService: ArchitectService
) {

    @PostMapping
    fun create(@Valid @RequestBody architect: Architect): ResponseEntity<Architect> =
        ResponseEntity(architectService.create(architect), HttpStatus.CREATED)

    @GetMapping("/{id}")
    fun find(@PathVariable id: Int): Architect = architectService.find(id)

    @GetMapping("/")
    fun findAll(): List<Architect> = architectService.findAll()
}
