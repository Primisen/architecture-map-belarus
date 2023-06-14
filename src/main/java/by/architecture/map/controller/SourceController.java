package by.architecture.map.controller;

import by.architecture.map.dto.SourceDto;
import by.architecture.map.entity.Source;
import by.architecture.map.exception.SourceException;
import by.architecture.map.service.SourceService;
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
@RequestMapping("/sources")
@CrossOrigin(origins = "http://localhost:7200")
public class SourceController {

    @Autowired
    private SourceService sourceService;

    @PostMapping("/")
    public void add(@RequestBody SourceDto sourceDto) throws SourceException {
        sourceService.add(sourceDto);
    }

    @GetMapping("/")
    public List<Source> findAll(){
        return sourceService.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) throws SourceException {
        sourceService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody SourceDto sourceUpdates) throws SourceException {
        sourceService.update(id, sourceUpdates);
    }
}
