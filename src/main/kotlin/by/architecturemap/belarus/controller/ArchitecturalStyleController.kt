package by.architecturemap.belarus.controller

import by.architecturemap.belarus.dto.ArchitecturalStyleDTO
import by.architecturemap.belarus.entity.ArchitecturalStyle
import by.architecturemap.belarus.service.ArchitecturalStyleService
import jakarta.validation.Valid
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
    fun create(@Valid @RequestBody architecturalStyle: ArchitecturalStyle): ResponseEntity<ArchitecturalStyle> =
        ResponseEntity(architecturalStyleService.create(architecturalStyle), HttpStatus.CREATED)

    @GetMapping("/")
    fun findAll(): List<ArchitecturalStyle> = architecturalStyleService.findAll()

    @GetMapping("/{id}")
    fun find(@PathVariable id: Int): ArchitecturalStyle = architecturalStyleService.find(id)

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Int,
        @Valid @RequestBody styleUpdates: ArchitecturalStyle
    ): ResponseEntity<ArchitecturalStyle> =
        ResponseEntity(architecturalStyleService.update(id, styleUpdates), HttpStatus.NO_CONTENT)

    @PatchMapping("/{id}")
    fun patchUpdate(
        @PathVariable id: Int,
        @RequestBody architecturalStyle: ArchitecturalStyleDTO
    ): ResponseEntity<ArchitecturalStyle> =
        ResponseEntity(architecturalStyleService.patchUpdate(id, architecturalStyle), HttpStatus.NO_CONTENT)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<String> =
        architecturalStyleService.delete(id).let { ResponseEntity(HttpStatus.NO_CONTENT) }
}
