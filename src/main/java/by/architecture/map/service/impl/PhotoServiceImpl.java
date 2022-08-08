package by.architecture.map.service.impl;

import by.architecture.map.dto.PhotoDto;
import by.architecture.map.entity.Construction;
import by.architecture.map.entity.Photo;
import by.architecture.map.mapper.PhotoMapper;
import by.architecture.map.repository.PhotoRepository;
import by.architecture.map.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private PhotoMapper photoMapper;

    @Override
    public List<Photo> findAllByConstruction(Construction construction) {
        return photoRepository.findAllByConstruction(construction);
    }

    @Override
    public void add(PhotoDto photoDto) {

        Photo photo = photoMapper.photoDtoToPhoto(photoDto);
        photoRepository.save(photo);
    }

    @Override
    public void delete(UUID id) {
        photoRepository.deleteById(id);
    }

    @Override
    public void update(UUID idOfOldPhoto, PhotoDto updatedPhoto) {

        Photo oldPhoto = photoRepository.findById(idOfOldPhoto).get();
        oldPhoto.setUrlAddressToPhoto(updatedPhoto.getUrlAddressToPhoto());
        oldPhoto.setConstruction(updatedPhoto.getConstruction());
        photoRepository.save(oldPhoto);
    }
}
