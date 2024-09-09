package by.architecture_map.belarus.repository.jpa

import by.architecture_map.belarus.entity.ConstructionImage
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ConstructionImageRepository : JpaRepository<ConstructionImage, Int> {

    @Query(
            value = "SELECT * FROM construction_image JOIN image ON image_id = id WHERE show = true AND id NOT IN (:usedImageId) ORDER BY random() LIMIT 20",
            nativeQuery = true
    )
    fun getRandomAndUniqueImages(@Param("usedImageId") usedImageId: IntArray): List<ConstructionImage>

    fun findByConstructionArchitecturalStyleId(architecturalStyleId: Int): List<ConstructionImage>

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
    fun getImagesOfConstructionsWithSameArchitecturalStyleByConstructionId(
        @Param("constructionId") constructionId: Int,
        @Param("architecturalStyleId") architecturalStyleId: Int?
    ): List<ConstructionImage>
}
