package com.architecture_map.belarus.service;

import com.architecture_map.belarus.dto.ConstructionImageDto;
import com.architecture_map.belarus.entity.image.ConstructionImage;

import java.util.Set;

public interface ConstructionImageService {

    ConstructionImage create(ConstructionImageDto image);

    Set<ConstructionImage> getRandomAndUniqueImages(String gotImagesId);

    Set<ConstructionImage> getByConstructionArchitecturalStyleId(Integer architecturalStyleId);

    // If you can propose more descriptive name for this method please share <3
    Set<ConstructionImage> getImagesWithSameArchitecturalStyleByConstructionIdAcrossImagesOfCurrentConstruction(Integer constructionId);

    boolean deleteById(Integer id);
}

