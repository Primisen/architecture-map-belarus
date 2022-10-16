package by.architecture.map.repository;

import by.architecture.map.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Integer> {
    @Query(nativeQuery=true, value="SELECT *  FROM photo ORDER BY random() LIMIT 18")//магическое число
    List<Photo> getRandomPhotos();

    boolean existsByUrlAddressToPhoto(String urlAddressToPhoto);
}