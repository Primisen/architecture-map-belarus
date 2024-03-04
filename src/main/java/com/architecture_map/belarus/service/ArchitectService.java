package com.architecture_map.belarus.service;

import com.architecture_map.belarus.entity.Architect;

import java.util.List;
import java.util.Optional;

public interface ArchitectService {

    Optional<Architect> findById(Integer id);

    List<Architect> findAll();
}
