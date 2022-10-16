package by.architecture.map.controller;

import by.architecture.map.dto.ConstructionDto;
import by.architecture.map.entity.Construction;
import by.architecture.map.service.ConstructionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequiredArgsConstructor
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
    public Construction getConstruction(@PathVariable Integer id){
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
    @PutMapping("/{idOfOldConstruction}")
    public void update(@PathVariable Integer idOfOldConstruction,
                       @RequestBody ConstructionDto updatedConstruction) {
        constructionService.update(idOfOldConstruction, updatedConstruction);
    }
}
