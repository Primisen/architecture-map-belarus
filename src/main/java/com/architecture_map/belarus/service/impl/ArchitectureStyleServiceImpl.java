package com.architecture_map.belarus.service.impl;

import com.architecture_map.belarus.dto.ArchitecturalStyleDto;
import com.architecture_map.belarus.entity.ArchitecturalStyle;
import com.architecture_map.belarus.exception.ArchitecturalStyleException;
import com.architecture_map.belarus.mapper.ArchitecturalStyleMapper;
import com.architecture_map.belarus.repository.ArchitecturalStyleRepository;
import com.architecture_map.belarus.service.ArchitectureStyleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArchitectureStyleServiceImpl implements ArchitectureStyleService {

    @Autowired
    private ArchitecturalStyleRepository architecturalStyleRepository;

    @Autowired
    private ArchitecturalStyleMapper architecturalStyleMapper;

    @Override
    public void add(ArchitecturalStyleDto architecturalStyleDto) throws ArchitecturalStyleException {

        if (architecturalStyleNotExists(architecturalStyleDto)) {
            save(architecturalStyleDto);

        } else {
            throw new ArchitecturalStyleException("Architectural style is exists.");
        }

    }

    @Override
    public void update(Integer id, ArchitecturalStyleDto styleUpdates) throws ArchitecturalStyleException {

        ArchitecturalStyle architecturalStyle = findById(id);
        if (styleUpdates.getName() != null) {
            architecturalStyle.setName(styleUpdates.getName());
        }

        architecturalStyleRepository.save(architecturalStyle);
    }

    @Override
    public void delete(Integer id) throws ArchitecturalStyleException {

        if (architecturalStyleIsExists(id)) {
            architecturalStyleRepository.deleteById(id);

        } else {
            throw new ArchitecturalStyleException("Architectural style not exists.");
        }
    }

    @Override
    public List<ArchitecturalStyle> findAll() {
        return architecturalStyleRepository.findAll();
    }

    private boolean architecturalStyleNotExists(ArchitecturalStyleDto architecturalStyle) {
        return !architecturalStyleIsExists(architecturalStyle);
    }

    private boolean architecturalStyleIsExists(ArchitecturalStyleDto architecturalStyle) {
        return architecturalStyleRepository.existsByName(architecturalStyle.getName());
    }

    private boolean architecturalStyleIsExists(Integer id) {
        return architecturalStyleRepository.existsById(id);
    }

    private void save(ArchitecturalStyleDto architecturalStyleDto) {

        architecturalStyleRepository.save(architecturalStyleMapper.toArchitecturalStyle(architecturalStyleDto));
    }

    private ArchitecturalStyle findById(Integer id) throws ArchitecturalStyleException {

        return architecturalStyleRepository.findById(id).orElseThrow(() -> new ArchitecturalStyleException("Architectural style with id = " + id + " not exists."));
    }

}
