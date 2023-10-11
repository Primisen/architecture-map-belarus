package com.architecture_map.belarus.controller;

import com.architecture_map.belarus.dto.SourceDto;
import com.architecture_map.belarus.entity.Source;
import com.architecture_map.belarus.exception.SourceException;
import com.architecture_map.belarus.service.SourceService;
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
@CrossOrigin(origins = "http://localhost:4200")
public class SourceController {

    @Autowired
    private SourceService sourceService;

    @PostMapping("/")
    public void add(@RequestBody SourceDto source) throws SourceException {
        sourceService.add(source);
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
