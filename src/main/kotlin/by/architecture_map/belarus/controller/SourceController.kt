package by.architecture_map.belarus.controller

import by.architecture_map.belarus.entity.Source
import by.architecture_map.belarus.service.SourceService
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
    fun create(@RequestBody source: Source): ResponseEntity<Source> {
        val createdSource = sourceService.create(source)
        return ResponseEntity(createdSource, HttpStatus.CREATED)
    }

    @GetMapping("/")
    fun findAll(): List<Source> {
        return sourceService.findAll()
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody source: Source): ResponseEntity<String> {
        sourceService.update(id, source)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @PatchMapping("/{id}")
    fun patch(@PathVariable id: Int, @RequestBody source: Source): ResponseEntity<String> {
        sourceService.patchUpdate(id, source)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<String> {
        sourceService.delete(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
