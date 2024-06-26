package com.architecture_map.belarus.service.impl;

import com.architecture_map.belarus.dto.ConstructionImageDto;
import com.architecture_map.belarus.entity.image.ConstructionImage;
import com.architecture_map.belarus.mapper.ConstructionImageMapper;
import com.architecture_map.belarus.repository.ConstructionImageRepository;
import com.architecture_map.belarus.repository.ConstructionRepository;
import com.architecture_map.belarus.repository.ImageRepository;
import com.architecture_map.belarus.service.ConstructionImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ConstructionImageServiceImpl implements ConstructionImageService {

    private final ConstructionImageRepository constructionImageRepository;
    private final ConstructionImageMapper constructionImageMapper;
    private final ConstructionRepository constructionRepository;
    private final ImageRepository imageRepository;

    @Override
    public ConstructionImage create(ConstructionImageDto image) {
        return constructionImageRepository.save(constructionImageMapper.toConstructionImage(image));
    }

    @Override
    public Set<ConstructionImage> getRandomAndUniqueImages(String gotImagesId) {
        return constructionImageRepository.getRandomAndUniqueImages(parseStringToArrayOfInt(gotImagesId));
    }

    @Override
    public Set<ConstructionImage> getByConstructionArchitecturalStyleId(Integer architecturalStyleId) {
        return constructionImageRepository.getByConstructionArchitecturalStyleId(architecturalStyleId);
    }

    @Override
    public Set<ConstructionImage> getImagesWithSameArchitecturalStyleByConstructionIdAcrossImagesOfCurrentConstruction(Integer constructionId) {
        return constructionImageRepository.getImagesWithSameArchitecturalStyleByConstructionIdAcrossImagesOfCurrentConstruction(
                constructionId,
                constructionRepository.findById(constructionId).orElseThrow().getArchitecturalStyle().getId()
        );
    }

    @Override
    public boolean deleteById(Integer id) {

        if (constructionImageRepository.existsById(id)) {
            constructionImageRepository.deleteById(id);
            return true;
        }

        return false;
    }

    private int[] parseStringToArrayOfInt(String string) {

        int[] arrayOfInt = new int[]{0};

        if (string != null) {

            String[] arrayOfStrings = string.split(",");
            arrayOfInt = new int[arrayOfStrings.length];

            for (int i = 0; i < arrayOfStrings.length; i++) {
                arrayOfInt[i] = Integer.parseInt(arrayOfStrings[i]);
            }
        }

        return arrayOfInt;
    }
}
