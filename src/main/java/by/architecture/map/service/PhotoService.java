package by.architecture.map.service;

import by.architecture.map.dto.PhotoDto;
import by.architecture.map.entity.Photo;

import java.util.List;

public interface PhotoService {

    List<Photo> findAllByConstruction(Integer constructionId);

    void add(PhotoDto photo);

    void delete(Integer id);

    void update(Integer idOfOldPhoto, PhotoDto updatedPhoto);
}
