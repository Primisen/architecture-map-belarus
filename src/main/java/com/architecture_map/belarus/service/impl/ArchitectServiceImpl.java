package com.architecture_map.belarus.service.impl;

import com.architecture_map.belarus.entity.Architect;
import com.architecture_map.belarus.repository.ArchitectRepository;
import com.architecture_map.belarus.service.ArchitectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArchitectServiceImpl implements ArchitectService {

    @Autowired
    private ArchitectRepository architectRepository;

    @Override
    public Architect findById(Integer id) {
        return architectRepository.findById(id).get();
    }

    @Override
    public List<Architect> findAll() {
        return architectRepository.findAll();
    }

}
