package by.architecture.map.repository;

import by.architecture.map.entity.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceRepository extends JpaRepository<Source, Integer> {
    boolean existsByUrl(String url);
}
