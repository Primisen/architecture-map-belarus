package by.architecture.map.service;

import by.architecture.map.dto.PhotoDto;
import by.architecture.map.dto.RandomPhotoDto;
import by.architecture.map.exception.ConstructionException;
import by.architecture.map.exception.PhotoException;
import by.architecture.map.exception.SourceException;

import java.util.List;

public interface PhotoService {

    List<RandomPhotoDto> getRandomPhotos();

    void add(PhotoDto photo) throws PhotoException;

    void update(Integer idOfOldPhoto, PhotoDto updatedPhoto) throws PhotoException, ConstructionException, SourceException;

    void delete(Integer id) throws PhotoException;
}
