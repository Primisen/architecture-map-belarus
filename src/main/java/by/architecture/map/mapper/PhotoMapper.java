package by.architecture.map.mapper;

import by.architecture.map.dto.PhotoDto;
import by.architecture.map.entity.Photo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper (componentModel = "spring")
public interface PhotoMapper {

    PhotoDto toPhotoDto(Photo photo);

    @Mapping(target = "construction.id", source = "constructionId")
    Photo toPhoto(PhotoDto photoDto);
}
