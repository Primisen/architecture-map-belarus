package com.architecture_map.belarus.service;

import com.architecture_map.belarus.dto.ConstructionDto;
import com.architecture_map.belarus.entity.Construction;

import java.util.List;
import java.util.Optional;

public interface ConstructionService {

    Construction create(ConstructionDto constructionDto);

    List<Construction> findAll();

    Optional<Construction> findById(Integer id);

    Optional<Construction> updateById(Integer id, ConstructionDto constructionDto);

    Optional<Construction> patchUpdateById(Integer id, ConstructionDto constructionDto);

    boolean deleteById(Integer id);

}
