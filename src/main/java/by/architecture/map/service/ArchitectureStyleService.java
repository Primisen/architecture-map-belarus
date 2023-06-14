package by.architecture.map.service;

import by.architecture.map.dto.ArchitecturalStyleDto;
import by.architecture.map.entity.ArchitecturalStyle;
import by.architecture.map.exception.ArchitecturalStyleException;

import java.util.List;

public interface ArchitectureStyleService {
    void add(ArchitecturalStyleDto architecturalStyleDto) throws ArchitecturalStyleException;

    void update(Integer id, ArchitecturalStyleDto styleUpdates) throws ArchitecturalStyleException;

    void delete(Integer id) throws ArchitecturalStyleException;

    List<ArchitecturalStyle> findAll();
}
