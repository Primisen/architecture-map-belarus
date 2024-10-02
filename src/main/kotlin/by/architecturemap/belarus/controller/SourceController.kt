package by.architecturemap.belarus.controller

import by.architecturemap.belarus.entity.Source
import by.architecturemap.belarus.service.SourceService
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
@RequestMapping("/sources")
@CrossOrigin(origins = ["http://localhost:7200", "http://localhost:4200", "*"])
class SourceController(
    private val sourceService: SourceService
) {

    @PostMapping("/")
    fun create(@Valid @RequestBody source: Source): ResponseEntity<Source> =
        ResponseEntity(sourceService.create(source), HttpStatus.CREATED)

    @GetMapping("/")
    fun findAll(): List<Source> = sourceService.findAll()

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody source: Source): ResponseEntity<Source> =
        ResponseEntity(sourceService.update(id, source), HttpStatus.NO_CONTENT)

    @PatchMapping("/{id}")
    fun patch(@PathVariable id: Int, @RequestBody source: Source): ResponseEntity<Source> =
        ResponseEntity(sourceService.patchUpdate(id, source), HttpStatus.NO_CONTENT)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<String> =
        sourceService.delete(id).let { ResponseEntity(HttpStatus.NO_CONTENT) }
}
