package by.architecture_map.belarus.controller

import by.architecture_map.belarus.entity.Architect
import by.architecture_map.belarus.service.ArchitectService
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
    fun create(@RequestBody architect: Architect): ResponseEntity<Architect> {
        val createdArchitect = architectService.create(architect)
        return ResponseEntity(createdArchitect, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun find(@PathVariable id: Int): Architect {
        return architectService.find(id)
    }

    @GetMapping("/")
    fun findAll(): List<Architect> {
        return architectService.findAll()
    }
}
