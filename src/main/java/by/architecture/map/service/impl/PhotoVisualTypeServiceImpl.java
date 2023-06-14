package by.architecture.map.service.impl;

import by.architecture.map.dto.PhotoVisualTypeDto;
import by.architecture.map.entity.PhotoVisualType;
import by.architecture.map.mapper.PhotoVisualTypeMapper;
import by.architecture.map.repository.PhotoVisualTypeRepository;
import by.architecture.map.service.PhotoVisualTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhotoVisualTypeServiceImpl implements PhotoVisualTypeService {

    @Autowired
    private PhotoVisualTypeRepository photoVisualTypeRepository;

    @Autowired
    private PhotoVisualTypeMapper photoVisualTypeMapper;

    @Override
    public List<PhotoVisualType> findAll() {
        return photoVisualTypeRepository.findAll();
    }

    @Override
    public void add(PhotoVisualTypeDto photoVisualTypeDto) {

        photoVisualTypeRepository.save(
                photoVisualTypeMapper.toVisualType(photoVisualTypeDto));
    }

    @Override
    public void update(Integer id, PhotoVisualTypeDto photoVisualTypeDto) {

        PhotoVisualType photoVisualType = photoVisualTypeRepository.findById(id).get();
        photoVisualType.setName(photoVisualTypeDto.getName());

        photoVisualTypeRepository.save(photoVisualType);
    }

    @Override
    public void delete(Integer id) {
        photoVisualTypeRepository.deleteById(id);
    }
}
