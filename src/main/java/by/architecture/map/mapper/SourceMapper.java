package by.architecture.map.mapper;

import by.architecture.map.dto.SourceDto;
import by.architecture.map.entity.Source;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SourceMapper {

    Source toSource(SourceDto sourceDto);

    SourceDto toSourceDto(Source source);
}
