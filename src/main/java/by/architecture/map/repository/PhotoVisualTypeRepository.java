package by.architecture.map.repository;

import by.architecture.map.entity.PhotoVisualType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoVisualTypeRepository extends JpaRepository<PhotoVisualType, Integer> {
    PhotoVisualType findByName(String name);
}
