package com.architecture_map.belarus.mapper;

import com.architecture_map.belarus.dto.ConstructionDto;
import com.architecture_map.belarus.entity.Construction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper (componentModel = "spring")
public interface ConstructionMapper {

    ConstructionDto toConstructionDto(Construction construction);

    @Mapping(target = "architecturalStyle.id", source = "architecturalStyleId")
    Construction toConstruction(ConstructionDto constructionDto);
}
