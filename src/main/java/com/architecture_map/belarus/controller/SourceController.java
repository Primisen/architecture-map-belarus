package com.architecture_map.belarus.controller;

import com.architecture_map.belarus.dto.SourceDto;
import com.architecture_map.belarus.entity.Source;
import com.architecture_map.belarus.exception.NotFoundException;
import com.architecture_map.belarus.service.SourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
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
@RequiredArgsConstructor
@RequestMapping("/sources")
@CrossOrigin(origins = {"http://localhost:7200", "http://localhost:4200", "*"})
public class SourceController {

    private final SourceService sourceService;

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody SourceDto source) {
        Source savedSource = sourceService.create(source);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", savedSource.getId().toString());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public List<Source> findAll() {
        return sourceService.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateById(@PathVariable Integer id, @RequestBody SourceDto sourceDto) {
        if (sourceService.updateById(id, sourceDto).isEmpty()) {
            throw new NotFoundException();
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> patchUpdateById(@PathVariable Integer id,
                                                  @RequestBody SourceDto source) {
        sourceService.patchUpdateById(id, source);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        if (!sourceService.deleteById(id)) {
            throw new NotFoundException();
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
