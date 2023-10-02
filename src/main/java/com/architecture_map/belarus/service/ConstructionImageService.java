package com.architecture_map.belarus.service;

import com.architecture_map.belarus.entity.image.ConstructionImage;

import java.util.List;

public interface ConstructionImageService {

    List<ConstructionImage> getRandomImage(String usedId);

}
