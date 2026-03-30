package by.architecture_map.belarus.controller;

import by.architecture_map.belarus.entity.Source;
import by.architecture_map.belarus.service.SourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sources")
@CrossOrigin(origins = {"http://localhost:7200", "http://localhost:4200", "*"})
@RequiredArgsConstructor
public class SourceController {

    private final SourceService sourceService;

    @PostMapping("/")
    public ResponseEntity<Source> create(@RequestBody Source source) {
        return new ResponseEntity<>(sourceService.create(source), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public List<Source> findAll() {
        return sourceService.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Source> update(@PathVariable int id, @RequestBody Source source) {
        return new ResponseEntity<>(sourceService.update(id, source), HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Source> patch(@PathVariable int id, @RequestBody Source source) {
        return new ResponseEntity<>(sourceService.patchUpdate(id, source), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        sourceService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
