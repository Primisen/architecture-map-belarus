package com.architecture_map.belarus.controller;

import com.architecture_map.belarus.dto.ArchitecturalStyleDto;
import com.architecture_map.belarus.entity.ArchitecturalStyle;
import com.architecture_map.belarus.exception.NotFoundException;
import com.architecture_map.belarus.service.ArchitectureStyleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/architectural-styles")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:7200", "http://localhost:4200", "*"})
public class ArchitecturalStyleController {

    private final ArchitectureStyleService architectureStyleService;

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody ArchitecturalStyleDto architecturalStyleDto) {

        ArchitecturalStyle savedArchitecturalStyle = architectureStyleService.create(architecturalStyleDto);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", savedArchitecturalStyle.getId().toString());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public List<ArchitecturalStyle> getAll() {
        return architectureStyleService.findAll();
    }

    @GetMapping("/{id}")
    public ArchitecturalStyle findById(@PathVariable Integer id) {
        return architectureStyleService.findById(id).orElseThrow(NotFoundException::new);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateById(@PathVariable Integer id, @RequestBody ArchitecturalStyleDto styleUpdates) {

        if (architectureStyleService.updateById(id, styleUpdates).isEmpty()) {
            throw new NotFoundException();
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        if (!architectureStyleService.deleteById(id)) {
            throw new NotFoundException();
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
