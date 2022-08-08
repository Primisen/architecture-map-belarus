package by.architecture.map.controller;

import by.architecture.map.entity.Construction;
import by.architecture.map.service.ConstructionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/constructions")
public class ConstructionController {

    @Autowired
    private ConstructionService constructionService;

    @GetMapping("/")
    public List<Construction> getAll() {
        return constructionService.findAll();
    }

    @PostMapping("/")
    public void add(@RequestBody Construction construction) {
        constructionService.add(construction);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        constructionService.delete(id);
    }

    @PutMapping("/{idOfOldConstruction}")
    public void update(@PathVariable UUID idOfOldConstruction, @RequestBody Construction updatedConstruction) {
        constructionService.update(idOfOldConstruction, updatedConstruction);
    }
}
