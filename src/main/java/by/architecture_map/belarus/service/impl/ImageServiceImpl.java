package by.architecture_map.belarus.service.impl;

import by.architecture_map.belarus.entity.Image;
import by.architecture_map.belarus.exception.NotFoundException;
import by.architecture_map.belarus.repository.jpa.ImageRepository;
import by.architecture_map.belarus.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    public Image find(int id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Image not found with id: " + id));
    }

}
