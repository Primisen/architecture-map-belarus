package by.architecture_map.belarus.service;

import by.architecture_map.belarus.entity.ConstructionImage;

import java.util.List;

public interface ConstructionImageService {

    ConstructionImage create(ConstructionImage image);

    List<ConstructionImage> find(int architecturalStyleId);

    /**
     * @param usedImagesId The construction image ids, which have already been obtained from the database and are keeping in order not to select them again
     */
    List<ConstructionImage> getRandomAndUniqueImages(String usedImagesId);

    /**
     * The method searches for images in the same architectural style such has construction.
     * At the same time, images of the construction itself are excluded from the answer
     */
    List<ConstructionImage> getImagesOfConstructionsWithSameArchitecturalStyle(int constructionId);

    void delete(int id);

}
