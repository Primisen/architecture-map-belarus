package com.architecture_map.belarus.mapper;

import com.architecture_map.belarus.dto.ConstructionImageDto;
import com.architecture_map.belarus.entity.image.ConstructionImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ConstructionImageMapper {

    @Mapping(target = "construction.id", source = "constructionId")
    @Mapping(target = "source.id", source = "sourceId")
    ConstructionImage toConstructionImage(ConstructionImageDto dto);
}
