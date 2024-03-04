package com.architecture_map.belarus.service.impl;

import com.architecture_map.belarus.dto.ArchitecturalStyleDto;
import com.architecture_map.belarus.entity.ArchitecturalStyle;
import com.architecture_map.belarus.mapper.ArchitecturalStyleMapper;
import com.architecture_map.belarus.repository.ArchitecturalStyleRepository;
import com.architecture_map.belarus.service.ArchitectureStyleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class ArchitectureStyleServiceImpl implements ArchitectureStyleService {

    private final ArchitecturalStyleRepository architecturalStyleRepository;
    private final ArchitecturalStyleMapper architecturalStyleMapper;

    @Override
    public ArchitecturalStyle create(ArchitecturalStyleDto architecturalStyleDto) {
        return architecturalStyleRepository.save(architecturalStyleMapper.toArchitecturalStyle(architecturalStyleDto));
    }

    @Override
    public Optional<ArchitecturalStyle> findById(Integer id) {
        return architecturalStyleRepository.findById(id);
    }

    @Override
    public List<ArchitecturalStyle> findAll() {
        return architecturalStyleRepository.findAll();
    }


    @Override
    public Optional<ArchitecturalStyle> updateById(Integer id, ArchitecturalStyleDto architecturalStyleDto) {

        AtomicReference<Optional<ArchitecturalStyle>> atomicReference = new AtomicReference<>();

        architecturalStyleRepository.findById(id).ifPresentOrElse(foundArchitecturalStyle -> {
            foundArchitecturalStyle.setName(architecturalStyleDto.getName());
            foundArchitecturalStyle.setAttributes(architecturalStyleDto.getArchitecturalAttributes());
            foundArchitecturalStyle.setDescription(architecturalStyleDto.getDescription());
            atomicReference.set(Optional.of(
                    architecturalStyleRepository.save(foundArchitecturalStyle)));
        }, () -> atomicReference.set(Optional.empty()));

        return atomicReference.get();
    }

    @Override
    public boolean deleteById(Integer id) {
        if (architecturalStyleRepository.existsById(id)) {
            architecturalStyleRepository.deleteById(id);
            return true;
        }

        return false;
    }
}
