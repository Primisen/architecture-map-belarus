package by.architecture_map.belarus.controller

import by.architecture_map.belarus.entity.ArchitecturalStyle
import by.architecture_map.belarus.service.ArchitecturalStyleService
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
    fun create(@RequestBody architecturalStyle: ArchitecturalStyle): ResponseEntity<ArchitecturalStyle> {
        val createdArchitecturalStyle = architecturalStyleService.create(architecturalStyle)
        return ResponseEntity(createdArchitecturalStyle, HttpStatus.CREATED)
    }

    @GetMapping("/")
    fun findAll(): List<ArchitecturalStyle> {
        return architecturalStyleService.findAll()
    }

    @GetMapping("/{id}")
    fun find(@PathVariable id: Int): ArchitecturalStyle {
        return architecturalStyleService.find(id)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody styleUpdates: ArchitecturalStyle): ResponseEntity<String> {
        architecturalStyleService.update(id, styleUpdates)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @PatchMapping("/{id}")
    fun patchUpdate(@PathVariable id: Int, @RequestBody architecturalStyle: ArchitecturalStyle): ResponseEntity<String> {
        architecturalStyleService.patchUpdate(id, architecturalStyle)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<String> {
        architecturalStyleService.delete(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
