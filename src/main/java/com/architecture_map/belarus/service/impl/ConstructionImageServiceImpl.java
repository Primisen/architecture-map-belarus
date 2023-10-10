package com.architecture_map.belarus.service.impl;

import com.architecture_map.belarus.entity.image.ConstructionImage;
import com.architecture_map.belarus.repository.ConstructionImageRepository;
import com.architecture_map.belarus.service.ConstructionImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConstructionImageServiceImpl implements ConstructionImageService {

    @Autowired
    private ConstructionImageRepository constructionImageRepository;

    @Override
    public List<ConstructionImage> getRandomImage(String usedId) {

        int[] usedImaageId = {0};
        //horrible
        if (usedId != null) {
    
            String[] strings = usedId.split(",");
            usedImaageId = new int[strings.length];

            for (int i = 0; i < strings.length; i++) {
                usedImaageId[i] = Integer.parseInt(strings[i]);
            }
            //horrible
        }
        return constructionImageRepository.getRandomImage(usedImaageId);
    }

    @Override
    public List<ConstructionImage> getByConstructionArchitecturalStyleId(Integer id) {
        return constructionImageRepository.getByConstructionArchitecturalStyleId(id);
    }

}
