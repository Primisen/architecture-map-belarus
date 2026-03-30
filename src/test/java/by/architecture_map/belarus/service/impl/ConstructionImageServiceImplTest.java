package by.architecture_map.belarus.service.impl;

import by.architecture_map.belarus.entity.Address;
import by.architecture_map.belarus.entity.ArchitecturalStyle;
import by.architecture_map.belarus.entity.Construction;
import by.architecture_map.belarus.entity.ConstructionImage;
import by.architecture_map.belarus.service.ConstructionService;
import by.architecture_map.belarus.repository.jpa.ConstructionImageRepository;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConstructionImageServiceImplTest {

    @Mock
    private ConstructionImageRepository constructionImageRepository;

    @Mock
    private ConstructionService constructionService;

    @InjectMocks
    private ConstructionImageServiceImpl constructionImageService;

    private ConstructionImage buildImage(String constructionName) {
        Address address = new Address();
        address.setRegion("Test");

        ArchitecturalStyle style = new ArchitecturalStyle();
        style.setName("Style");

        Construction construction = new Construction();
        construction.setName(constructionName);
        construction.setAddress(address);
        construction.setArchitecturalStyle(style);

        ConstructionImage image = new ConstructionImage();
        image.setConstruction(construction);
        image.setTakenTime("пач. XX ст");

        return image;
    }

    @Test
    void whenCreateConstructionImage_thenSaveImage() {
        // given
        ConstructionImage image = buildImage("Name");
        when(constructionImageRepository.save(image)).thenReturn(image);

        // when
        ConstructionImage result = constructionImageService.create(image);

        // then
        verify(constructionImageRepository, times(1)).save(image);
        assertEquals(image, result);
    }

    @Test
    void whenGetRandomAndUniqueImages_thenReturnListOfImages() {
        // given
        String imageIds = "1,2,3";
        List<ConstructionImage> images = Arrays.asList(buildImage("Name1"), buildImage("Name2"));
        when(constructionImageRepository.getRandomAndUniqueImages(new int[]{1, 2, 3})).thenReturn(images);

        // when
        List<ConstructionImage> result = constructionImageService.getRandomAndUniqueImages(imageIds);

        // then
        verify(constructionImageRepository, times(1)).getRandomAndUniqueImages(new int[]{1, 2, 3});
        assertEquals(images, result);
    }

    @Test
    void whenFindConstructionImages_thenReturnListOfImages() {
        // given
        int styleId = 1;
        List<ConstructionImage> images = Arrays.asList(buildImage("Name1"), buildImage("Name2"));
        when(constructionImageRepository.findByConstructionArchitecturalStyleId(styleId)).thenReturn(images);

        // when
        List<ConstructionImage> result = constructionImageService.find(styleId);

        // then
        verify(constructionImageRepository, times(1)).findByConstructionArchitecturalStyleId(styleId);
        assertEquals(images, result);
    }

}