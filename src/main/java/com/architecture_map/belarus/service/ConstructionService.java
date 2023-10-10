package com.architecture_map.belarus.service;

import com.architecture_map.belarus.dto.ConstructionDto;
import com.architecture_map.belarus.entity.Construction;

import java.util.List;

public interface ConstructionService {

    List<Construction> findAll();

    Construction findById(Integer id);

    void add(ConstructionDto construction);

    void update(Integer idOfOldConstruction, ConstructionDto updatedConstruction);

    void delete(Integer id);
}
