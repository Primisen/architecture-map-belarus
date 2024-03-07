package com.architecture_map.belarus.service;

import com.architecture_map.belarus.dto.ConstructionImageDto;
import com.architecture_map.belarus.entity.image.ConstructionImage;

import java.util.Set;

public interface ConstructionImageService {

    ConstructionImage create(ConstructionImageDto image);

    Set<ConstructionImage> getRandomAndUniqueImages(String gotImagesId);

    Set<ConstructionImage> getByConstructionArchitecturalStyleId(Integer architecturalStyleId);
}
