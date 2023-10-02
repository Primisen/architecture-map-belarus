package com.architecture_map.belarus.repository;

import com.architecture_map.belarus.entity.Construction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConstructionRepository extends JpaRepository<Construction, Integer> {

    List<Construction> findAllByArchitecturalStyleId(Integer architecturalStyleId);
}
