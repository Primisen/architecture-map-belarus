package com.architecture_map.belarus.service;

import com.architecture_map.belarus.dto.ArchitecturalStyleDto;
import com.architecture_map.belarus.entity.ArchitecturalStyle;

import java.util.List;
import java.util.Optional;

public interface ArchitectureStyleService {
    ArchitecturalStyle create(ArchitecturalStyleDto architecturalStyleDto);

    List<ArchitecturalStyle> findAll();

    Optional<ArchitecturalStyle> findById(Integer id);

    Optional<ArchitecturalStyle> updateById(Integer id, ArchitecturalStyleDto styleUpdates);

    Optional<ArchitecturalStyle>  patchUpdateById(Integer id, ArchitecturalStyleDto architecturalStyleDto);

    boolean deleteById(Integer id);
}
