package by.architecture_map.belarus.controller;

import by.architecture_map.belarus.entity.Architect;
import by.architecture_map.belarus.service.ArchitectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/architects")
@CrossOrigin(origins = {"http://localhost:7200", "http://localhost:4200", "*"})
@RequiredArgsConstructor
public class ArchitectController {

    private final ArchitectService architectService;

    @PostMapping
    public ResponseEntity<Architect> create(@RequestBody Architect architect) {
        return new ResponseEntity<>(architectService.create(architect), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Architect find(@PathVariable int id) {
        return architectService.find(id);
    }

    @GetMapping("/")
    public List<Architect> findAll() {
        return architectService.findAll();
    }

}
