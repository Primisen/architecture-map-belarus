package com.architecture_map.belarus.repository;

import com.architecture_map.belarus.entity.image.ConstructionImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConstructionImageRepository extends JpaRepository<ConstructionImage, Integer> {

    @Query(
            value = "SELECT *  FROM construction_image JOIN image ON image_id=id where show='true' AND id NOT IN (?1) ORDER BY random() LIMIT 20 ",
            nativeQuery = true)
    List<ConstructionImage> getRandomImage(int[] usedId);

}
