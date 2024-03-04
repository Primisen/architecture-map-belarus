package com.architecture_map.belarus.controller;

import com.architecture_map.belarus.dto.ConstructionDto;
import com.architecture_map.belarus.entity.Construction;
import com.architecture_map.belarus.exception.NotFoundException;
import com.architecture_map.belarus.service.ConstructionService;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/constructions")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:7200"})
public class ConstructionController {

    private final ConstructionService constructionService;

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody ConstructionDto construction) {
        Construction savedConstruction = constructionService.create(construction);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", savedConstruction.getId().toString());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public List<Construction> getAll() {
        return constructionService.findAll();
    }

    @GetMapping("/{id}")
    public Construction getById(@PathVariable Integer id) {
        return constructionService.findById(id).orElseThrow(NotFoundException::new);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateById(@PathVariable Integer id,
                                             @RequestBody ConstructionDto constructionUpdates) {
        if (constructionService.updateBuId(id, constructionUpdates).isEmpty()) {
            throw new NotFoundException();
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> patchUpdateById(@PathVariable Integer id,
                                                  @RequestBody ConstructionDto construction) {
        constructionService.patchUpdateBuId(id, construction);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {

        if (!constructionService.deleteById(id)) {
            throw new NotFoundException();
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
