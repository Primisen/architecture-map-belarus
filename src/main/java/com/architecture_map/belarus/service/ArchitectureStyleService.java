package com.architecture_map.belarus.service;

import com.architecture_map.belarus.dto.ArchitecturalStyleDto;
import com.architecture_map.belarus.entity.ArchitecturalStyle;
import com.architecture_map.belarus.exception.ArchitecturalStyleException;

import java.util.List;

public interface ArchitectureStyleService {
    void add(ArchitecturalStyleDto architecturalStyleDto) throws ArchitecturalStyleException;

    void update(Integer id, ArchitecturalStyleDto styleUpdates) throws ArchitecturalStyleException;

    void delete(Integer id) throws ArchitecturalStyleException;

    List<ArchitecturalStyle> findAll();

    ArchitecturalStyle findById(Integer id) throws ArchitecturalStyleException;
}
