package by.architecture.map.repository;

import by.architecture.map.entity.Construction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ConstructionRepository extends JpaRepository<Construction, UUID> {
}
