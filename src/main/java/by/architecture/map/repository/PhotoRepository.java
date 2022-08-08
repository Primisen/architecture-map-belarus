package by.architecture.map.repository;

import by.architecture.map.entity.Construction;
import by.architecture.map.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, UUID> {
    List<Photo> findAllByConstruction(Construction construction);
}
