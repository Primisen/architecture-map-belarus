package com.architecture_map.belarus.service;

import com.architecture_map.belarus.entity.Architect;

import java.util.List;

public interface ArchitectService {

    Architect findById(Integer id);

    List<Architect> findAll();
}
