package by.architecture_map.belarus.controller;

import by.architecture_map.belarus.entity.ArchitecturalStyle;
import by.architecture_map.belarus.service.ArchitecturalStyleService;
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
@RequestMapping("/architectural-styles")
@CrossOrigin(origins = {"http://localhost:7200", "http://localhost:4200", "*"})
@RequiredArgsConstructor
public class ArchitecturalStyleController {

    private final ArchitecturalStyleService architecturalStyleService;

    @PostMapping("/")
    public ResponseEntity<ArchitecturalStyle> create(@RequestBody ArchitecturalStyle architecturalStyle) {
        return new ResponseEntity<>(architecturalStyleService.create(architecturalStyle), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public List<ArchitecturalStyle> findAll() {
        return architecturalStyleService.findAll();
    }

    @GetMapping("/{id}")
    public ArchitecturalStyle find(@PathVariable int id) {
        return architecturalStyleService.find(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArchitecturalStyle> update(
            @PathVariable int id,
            @RequestBody ArchitecturalStyle styleUpdates) {
        return new ResponseEntity<>(architecturalStyleService.update(id, styleUpdates), HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ArchitecturalStyle> patchUpdate(
            @PathVariable int id,
            @RequestBody ArchitecturalStyle architecturalStyle) {
        return new ResponseEntity<>(architecturalStyleService.patchUpdate(id, architecturalStyle), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        architecturalStyleService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
