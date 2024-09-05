package by.architecture_map.belarus.controller

import by.architecture_map.belarus.entity.ArchitecturalStyle
import by.architecture_map.belarus.exception.NotFoundException
import by.architecture_map.belarus.service.ArchitecturalStyleService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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
@RequestMapping("/architectural-styles")
@CrossOrigin(origins = ["http://localhost:7200", "http://localhost:4200", "*"])
class ArchitecturalStyleController(
        private val architecturalStyleService: ArchitecturalStyleService
) {

    @PostMapping("/")
    fun create(@RequestBody architecturalStyle: ArchitecturalStyle): ResponseEntity<String> {
        val savedArchitecturalStyle = architecturalStyleService.create(architecturalStyle)

        val headers = HttpHeaders()
        headers.add("Location", savedArchitecturalStyle.id.toString())

        return ResponseEntity(headers, HttpStatus.CREATED)
    }

    @GetMapping("/")
    fun findAll(): List<ArchitecturalStyle> {
        return architecturalStyleService.findAll()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): ArchitecturalStyle {
        return architecturalStyleService.findById(id)
                ?: throw NotFoundException("Architectural style not found with id: $id")
    }

    @PutMapping("/{id}")
    fun updateById(@PathVariable id: Int, @RequestBody styleUpdates: ArchitecturalStyle): ResponseEntity<String> {
        if (architecturalStyleService.updateById(id, styleUpdates) == null) {
            throw NotFoundException("Architectural style not found with id: $id")
        }
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @PatchMapping("/{id}")
    fun patchById(@PathVariable id: Int, @RequestBody architecturalStyle: ArchitecturalStyle): ResponseEntity<String> {
        architecturalStyleService.patchById(id, architecturalStyle)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Int): ResponseEntity<String> {
        if (!architecturalStyleService.deleteById(id)) {
            throw NotFoundException("Architectural style not found with id: $id")
        }
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}