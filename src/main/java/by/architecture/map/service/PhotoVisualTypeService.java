package by.architecture.map.service;

import by.architecture.map.dto.PhotoVisualTypeDto;
import by.architecture.map.entity.PhotoVisualType;

import java.util.List;

public interface PhotoVisualTypeService {
    void add(PhotoVisualTypeDto photoVisualTypeDto);

    List<PhotoVisualType> findAll();

    void update(Integer id, PhotoVisualTypeDto photoVisualTypeDto);

    void delete(Integer id);
}
