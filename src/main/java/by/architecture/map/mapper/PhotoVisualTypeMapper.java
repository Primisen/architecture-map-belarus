package by.architecture.map.mapper;

import by.architecture.map.dto.PhotoVisualTypeDto;
import by.architecture.map.entity.PhotoVisualType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PhotoVisualTypeMapper {

    PhotoVisualType toVisualType(PhotoVisualTypeDto photoVisualTypeDto);
}
