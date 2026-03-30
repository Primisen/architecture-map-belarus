package by.architecture_map.belarus.service.impl;

import by.architecture_map.belarus.entity.ArchitecturalStyle;
import by.architecture_map.belarus.entity.ConstructionImage;
import by.architecture_map.belarus.repository.jpa.ConstructionImageRepository;
import by.architecture_map.belarus.service.ConstructionImageService;
import by.architecture_map.belarus.service.ConstructionService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConstructionImageServiceImpl implements ConstructionImageService {

    private final ConstructionImageRepository constructionImageRepository;
    private final ConstructionService constructionService;

    @Override
    public ConstructionImage create(ConstructionImage image) {
        return constructionImageRepository.save(image);
    }

    @Override
    public List<ConstructionImage> getRandomAndUniqueImages(String usedImagesId) {
        return constructionImageRepository.getRandomAndUniqueImages(parseStringToIntArray(usedImagesId));
    }

    @Override
    public List<ConstructionImage> find(int architecturalStyleId) {
        return constructionImageRepository.findByConstructionArchitecturalStyleId(architecturalStyleId);
    }

    @Override
    public List<ConstructionImage> getImagesOfConstructionsWithSameArchitecturalStyle(int constructionId) {
        ArchitecturalStyle architecturalStyle = constructionService.find(constructionId).getArchitecturalStyle();
        Integer architecturalStyleId = architecturalStyle != null ? architecturalStyle.getId() : null;
        return constructionImageRepository.getImagesOfConstructionsWithSameArchitecturalStyleByConstructionId(
                constructionId,
                architecturalStyleId
        );
    }

    @Override
    public void delete(int id) {
        find(id);
        constructionImageRepository.deleteById(id);
    }

    private int[] parseStringToIntArray(String numbers) {
        if (numbers == null || numbers.isEmpty())
            return new int[]{0};
        return Arrays.stream(numbers.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

}
