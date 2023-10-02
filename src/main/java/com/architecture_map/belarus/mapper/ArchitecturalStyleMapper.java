package com.architecture_map.belarus.mapper;

import com.architecture_map.belarus.dto.ArchitecturalStyleDto;
import com.architecture_map.belarus.entity.ArchitecturalStyle;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface ArchitecturalStyleMapper {

    ArchitecturalStyle toArchitecturalStyle(ArchitecturalStyleDto architecturalStyleDto);
}
