package by.architecture.map.mapper;

import by.architecture.map.dto.ArchitecturalStyleDto;
import by.architecture.map.entity.ArchitecturalStyle;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface ArchitecturalStyleMapper {

    ArchitecturalStyle toArchitecturalStyle(ArchitecturalStyleDto architecturalStyleDto);
}
