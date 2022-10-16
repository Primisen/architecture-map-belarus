package by.architecture.map.mapper;

import by.architecture.map.dto.PhotoDto;
import by.architecture.map.dto.RandomPhotoDto;
import by.architecture.map.entity.Photo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper (componentModel = "spring")
public interface PhotoMapper {

    PhotoDto toPhotoDto(Photo photo);

    @Mapping(target = "construction.id", source = "constructionId")
    @Mapping(target = "source.id", source = "sourceId")
    Photo toPhoto(PhotoDto photoDto);

    @Mapping(target = "constructionName", source = "construction.name")
    @Mapping(target = "address", source = "construction.address")
    @Mapping(target = "constructionId", source = "construction.id")
    RandomPhotoDto toRandomPhotoDto(Photo photo);

}
