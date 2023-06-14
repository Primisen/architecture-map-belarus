package by.architecture.map.controller;

import by.architecture.map.dto.ArchitecturalStyleDto;
import by.architecture.map.entity.ArchitecturalStyle;
import by.architecture.map.exception.ArchitecturalStyleException;
import by.architecture.map.service.ArchitectureStyleService;
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
@RequestMapping("/architectural-styles")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:7200")
public class ArchitecturalStyleController {

    @Autowired
    private ArchitectureStyleService architectureStyleService;

    @GetMapping("/")
    public List<ArchitecturalStyle> getAll() {
        return architectureStyleService.findAll();
    }

    @PostMapping("/")
    public void add(@RequestBody ArchitecturalStyleDto architecturalStyleDto) throws ArchitecturalStyleException {
        architectureStyleService.add(architecturalStyleDto);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody ArchitecturalStyleDto styleUpdates) throws ArchitecturalStyleException {
        architectureStyleService.update(id, styleUpdates);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) throws ArchitecturalStyleException {
        architectureStyleService.delete(id);
    }
}
