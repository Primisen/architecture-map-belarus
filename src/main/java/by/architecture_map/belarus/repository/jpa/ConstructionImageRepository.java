package by.architecture_map.belarus.repository.jpa;

import by.architecture_map.belarus.entity.ConstructionImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConstructionImageRepository extends JpaRepository<ConstructionImage, Integer> {

    @Query(
            value = "SELECT * FROM construction_image JOIN image ON image_id = id WHERE show = true AND id NOT IN (:usedImageId) ORDER BY random() LIMIT 20",
            nativeQuery = true
    )
    List<ConstructionImage> getRandomAndUniqueImages(@Param("usedImageId") int[] usedImageId);

    List<ConstructionImage> findByConstructionArchitecturalStyleId(int architecturalStyleId);

    @Query(
            value = """
            SELECT * FROM construction_image
            JOIN image ON image_id = id
            JOIN construction ON construction_image.construction_id = construction.id
                AND construction.architectural_style_id = :architecturalStyleId
            WHERE show = true
              AND construction_image.construction_id NOT IN (:constructionId)
        """,
            nativeQuery = true
    )
    List<ConstructionImage> getImagesOfConstructionsWithSameArchitecturalStyleByConstructionId(
            @Param("constructionId") int constructionId,
            @Param("architecturalStyleId") Integer architecturalStyleId
    );

}
