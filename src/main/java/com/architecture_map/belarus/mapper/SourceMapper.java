package com.architecture_map.belarus.mapper;

import com.architecture_map.belarus.dto.SourceDto;
import com.architecture_map.belarus.entity.Source;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SourceMapper {

    Source toSource(SourceDto sourceDto);

    SourceDto toSourceDto(Source source);
}
