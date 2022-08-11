package by.architecture.map.mapper;

import by.architecture.map.dto.PhotoDto;
import by.architecture.map.entity.Photo;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface PhotoMapper {

    PhotoDto photoToPhotoDto(Photo photo);
    Photo photoDtoToPhoto(PhotoDto photoDto);
}
