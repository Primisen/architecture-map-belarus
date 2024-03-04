package com.architecture_map.belarus.controller;

import com.architecture_map.belarus.dto.ConstructionDto;
import com.architecture_map.belarus.entity.Construction;
import com.architecture_map.belarus.service.ConstructionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
@AllArgsConstructor
@RequestMapping("/constructions")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:7200"})
public class ConstructionController {

    @Autowired
    private ConstructionService constructionService;

    @Operation(summary = "Get all existing constructions")
    @GetMapping("/")
    public List<Construction> getAll() {
        return constructionService.findAll();
    }

    @Operation(summary = "Get all information about construction")
    @GetMapping("/{id}")
    public Construction getById(@PathVariable Integer id){
        return constructionService.findById(id);
    }

    @Operation(summary = "Adding a new construction")
    @PostMapping("/")
    public void add(@RequestBody ConstructionDto construction) {
        constructionService.add(construction);
    }

    @Operation(summary = "Delete construction by id")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        constructionService.delete(id);
    }

    @Operation(summary = "Change an existing construction")
    @PutMapping("/{id}")
    public void update(@PathVariable Integer id,
                       @RequestBody ConstructionDto constructionUpdates) {
        constructionService.update(id, constructionUpdates);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> partialUpdate(@PathVariable Integer id,
                                              @RequestBody ConstructionDto construction) {
        constructionService.partialUpdate(id, construction);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
