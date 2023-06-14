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
    @Mapping(target = "visualType.id", source = "visualTypeId")
    Photo toPhoto(PhotoDto photoDto);

    @Mapping(target = "address", source = "construction.address")
    @Mapping(target = "constructionId", source = "construction.id")
    @Mapping(target = "architecturalStyleName", source = "construction.architecturalStyle.name")
    RandomPhotoDto toRandomPhotoDto(Photo photo);

}
