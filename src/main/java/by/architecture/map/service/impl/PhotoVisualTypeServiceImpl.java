package by.architecture.map.service.impl;

import by.architecture.map.dto.PhotoVisualTypeDto;
import by.architecture.map.mapper.PhotoVisualTypeMapper;
import by.architecture.map.repository.PhotoVisualTypeRepository;
import by.architecture.map.service.PhotoVisualTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoVisualTypeServiceImpl implements PhotoVisualTypeService {

    @Autowired
    private PhotoVisualTypeRepository photoVisualTypeRepository;

    @Autowired
    private PhotoVisualTypeMapper photoVisualTypeMapper;

    @Override
    public void add(PhotoVisualTypeDto photoVisualTypeDto) {


        photoVisualTypeRepository.save(
                photoVisualTypeMapper.toVisualType(photoVisualTypeDto));
    }
}
