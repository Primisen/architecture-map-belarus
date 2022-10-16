package by.architecture.map.service.impl;

import by.architecture.map.dto.PhotoDto;
import by.architecture.map.dto.RandomPhotoDto;
import by.architecture.map.entity.Photo;
import by.architecture.map.exception.ConstructionException;
import by.architecture.map.exception.PhotoException;
import by.architecture.map.exception.SourceException;
import by.architecture.map.mapper.PhotoMapper;
import by.architecture.map.repository.ConstructionRepository;
import by.architecture.map.repository.PhotoRepository;
import by.architecture.map.repository.SourceRepository;
import by.architecture.map.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoRepository photoRepository;
    @Autowired
    private ConstructionRepository constructionRepository;
    @Autowired
    private SourceRepository sourceRepository;

    @Autowired
    private PhotoMapper photoMapper;

    @Override
    public List<RandomPhotoDto> getRandomPhotos() {

        List<RandomPhotoDto> photos = new ArrayList<>();

        for (Photo photo : photoRepository.getRandomPhotos()) {
            photos.add(photoMapper.toRandomPhotoDto(photo));
        }

        return photos;
    }

    @Override
    public void add(PhotoDto photo) throws PhotoException {

        if (photoNotExists(photo)) {
            save(photo);

        } else {
            throw new PhotoException("Photo already exists.");
        }
    }

    @Override
    public void update(Integer id, PhotoDto photoUpdates) throws PhotoException, ConstructionException, SourceException {

        Photo photo = findPhotoById(id);

        updateUrlAddressToPhoto(photo, photoUpdates);
        updateConstruction(photo, photoUpdates);
        updateSource(photo, photoUpdates);

        photoRepository.save(photo);
    }

    @Override
    public void delete(Integer id) throws PhotoException {

        if (photoIsExists(id)) {
            photoRepository.deleteById(id);

        } else {
            throw new PhotoException("Photo not exists.");
        }
    }

    private Photo findPhotoById(Integer id) throws PhotoException {
        return photoRepository.findById(id)
                .orElseThrow(() -> new PhotoException("Photo with id = " + id + "not exists."));
    }

    private void updateUrlAddressToPhoto(Photo photo, PhotoDto photoUpdates) {
        if (photoUpdates.getUrlAddressToPhoto() != null) {
            photo.setUrlAddressToPhoto(photoUpdates.getUrlAddressToPhoto());
        }
    }

    private void updateConstruction(Photo photo, PhotoDto photoUpdates) throws ConstructionException {
        if (photoUpdates.getConstructionId() != 0) {
            photo.setConstruction(constructionRepository.findById(photoUpdates.getConstructionId())
                    .orElseThrow(() -> new ConstructionException("Construction with id = " + photoUpdates.getConstructionId() + " not exists.")));
        }
    }

    private void updateSource(Photo photo, PhotoDto photoUpdates) throws SourceException {
        if (photoUpdates.getSourceId() != 0) {
            photo.setSource(sourceRepository.findById(photoUpdates.getSourceId())
                    .orElseThrow(() -> new SourceException("Source with id = " + photoUpdates.getSourceId() + " not exists.")));
        }
    }

    private void save(PhotoDto photoDto) {
        Photo photo = photoMapper.toPhoto(photoDto);
        photoRepository.save(photo);
    }

    private boolean photoNotExists(PhotoDto photo) {
        return !photoIsExists(photo);
    }

    private boolean photoIsExists(PhotoDto photo) {
        return photoRepository.existsByUrlAddressToPhoto(photo.getUrlAddressToPhoto());
    }

    private boolean photoIsExists(Integer id) {
        return photoRepository.existsById(id);
    }
}
