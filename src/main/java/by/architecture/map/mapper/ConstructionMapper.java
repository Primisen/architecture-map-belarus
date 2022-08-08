package by.architecture.map.mapper;

import by.architecture.map.dto.ConstructionDto;
import by.architecture.map.entity.Construction;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface ConstructionMapper {

    ConstructionDto constructionToConstructionDto(Construction construction);
    Construction constructionDtoToConstruction(ConstructionDto constructionDto);
}
