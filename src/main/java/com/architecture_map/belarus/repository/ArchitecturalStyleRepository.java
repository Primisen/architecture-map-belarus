package com.architecture_map.belarus.repository;

import com.architecture_map.belarus.entity.ArchitecturalStyle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchitecturalStyleRepository extends JpaRepository<ArchitecturalStyle, Integer> {

    boolean existsByName(String name);
}
