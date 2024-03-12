package com.architecture_map.belarus.repository;

import com.architecture_map.belarus.entity.Architect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchitectRepository extends JpaRepository<Architect, Integer> {
}
