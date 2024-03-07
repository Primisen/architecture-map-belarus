package com.architecture_map.belarus.repository;

import com.architecture_map.belarus.entity.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceRepository extends JpaRepository<Source, Integer> {
}
