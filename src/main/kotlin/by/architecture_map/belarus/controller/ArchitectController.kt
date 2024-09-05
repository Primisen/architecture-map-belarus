package by.architecture_map.belarus.controller

import by.architecture_map.belarus.entity.Architect
import by.architecture_map.belarus.exception.NotFoundException
import by.architecture_map.belarus.service.ArchitectService
import org.springframework.http.HttpHeaders
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
    fun create(@RequestBody architect: Architect): ResponseEntity<String> {
        val savedArchitect = architectService.create(architect)

        val headers = HttpHeaders()
        headers.add("Location", savedArchitect.id.toString())

        return ResponseEntity(headers, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): Architect {
        return architectService.findById(id) ?: throw NotFoundException("Architect not found with id: $id")
    }

    @GetMapping("/")
    fun findAll(): List<Architect> {
        return architectService.findAll()
    }
}