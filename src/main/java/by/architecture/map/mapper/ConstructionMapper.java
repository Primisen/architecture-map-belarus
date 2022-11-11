package by.architecture.map.mapper;

import by.architecture.map.dto.ConstructionDto;
import by.architecture.map.entity.Construction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper (componentModel = "spring")
public interface ConstructionMapper {

    ConstructionDto toConstructionDto(Construction construction);

    @Mapping(target = "architecturalStyle.id", source = "architecturalStyleId")
    Construction toConstruction(ConstructionDto constructionDto);
}
