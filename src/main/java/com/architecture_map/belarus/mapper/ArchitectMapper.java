package com.architecture_map.belarus.mapper;

import com.architecture_map.belarus.dto.ArchitectDto;
import com.architecture_map.belarus.entity.Architect;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArchitectMapper {

    Architect mappingToArchitect(ArchitectDto architectDto);
}
