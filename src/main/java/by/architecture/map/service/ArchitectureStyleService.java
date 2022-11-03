package by.architecture.map.service;

import by.architecture.map.dto.ArchitecturalStyleDto;
import by.architecture.map.exception.ArchitecturalStyleException;

public interface ArchitectureStyleService {
    void add(ArchitecturalStyleDto architecturalStyleDto) throws ArchitecturalStyleException;

    void update(Integer id, ArchitecturalStyleDto styleUpdates) throws ArchitecturalStyleException;

    void delete(Integer id) throws ArchitecturalStyleException;
}
