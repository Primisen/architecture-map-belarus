package com.architecture_map.belarus.repository;

import com.architecture_map.belarus.entity.image.ConstructionImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ConstructionImageRepository extends JpaRepository<ConstructionImage, Integer> {

    @Query(
            value = "SELECT *  FROM construction_image JOIN image ON image_id=id WHERE show=true AND id NOT IN (?1) ORDER BY random() LIMIT 20",
            nativeQuery = true)
    Set<ConstructionImage> getRandomAndUniqueImages(int[] gotImageId);

    Set<ConstructionImage> getByConstructionArchitecturalStyleId(Integer architecturalStyleId);

    @Query(
            value = "SELECT *  FROM construction_image " +
                    "JOIN image ON image_id=id  " +
                    "JOIN construction ON construction_image.construction_id=construction.id AND construction.architectural_style_id=(?2) " +
                    "WHERE show=true " +
                    "AND construction_image.construction_id NOT IN (?1) ",
            nativeQuery = true)
    Set<ConstructionImage> getImagesWithSameArchitecturalStyleByConstructionIdAcrossImagesOfCurrentConstruction(int constructionId, int architecturalStyleId);
}
