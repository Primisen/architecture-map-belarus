package com.architecture_map.belarus.service.impl;

import com.architecture_map.belarus.dto.ArchitectDto;
import com.architecture_map.belarus.entity.Architect;
import com.architecture_map.belarus.mapper.ArchitectMapper;
import com.architecture_map.belarus.repository.ArchitectRepository;
import com.architecture_map.belarus.service.ArchitectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArchitectServiceImpl implements ArchitectService {

    private final ArchitectRepository architectRepository;
    private final ArchitectMapper architectMapper;

    @Override
    public Architect create(ArchitectDto architectDto) {
        return architectRepository.save(architectMapper.mappingToArchitect(architectDto));
    }

    @Override
    public Optional<Architect> findById(Integer id) {
        return architectRepository.findById(id);
    }

    @Override
    public List<Architect> findAll() {
        return architectRepository.findAll();
    }
}
