package com.architecture_map.belarus.service;

import com.architecture_map.belarus.dto.ConstructionImageDto;
import com.architecture_map.belarus.entity.image.ConstructionImage;

import java.util.List;

public interface ConstructionImageService {

    List<ConstructionImage> getRandomImage(String usedId);

    List<ConstructionImage> getByConstructionArchitecturalStyleId(Integer id);

    void save (ConstructionImageDto image);

}
