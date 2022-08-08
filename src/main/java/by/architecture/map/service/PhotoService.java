package by.architecture.map.service;

import by.architecture.map.dto.PhotoDto;
import by.architecture.map.entity.Construction;
import by.architecture.map.entity.Photo;

import java.util.List;
import java.util.UUID;

public interface PhotoService {

    List<Photo> findAllByConstruction(Construction construction);

    void add(PhotoDto photo);

    void delete(UUID id);

    void update(UUID idOfOldPhoto, PhotoDto updatedPhoto);
}
