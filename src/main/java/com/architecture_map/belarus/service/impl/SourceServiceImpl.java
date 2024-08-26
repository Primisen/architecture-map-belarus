package com.architecture_map.belarus.service.impl;

import com.architecture_map.belarus.dto.SourceDto;
import com.architecture_map.belarus.entity.Source;
import com.architecture_map.belarus.mapper.SourceMapper;
import com.architecture_map.belarus.repository.SourceRepository;
import com.architecture_map.belarus.service.SourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class SourceServiceImpl implements SourceService {

    private final SourceRepository sourceRepository;

    private final SourceMapper sourceMapper;

    @Override
    public Source create(SourceDto sourceDto) {
        return sourceRepository.save(sourceMapper.toSource(sourceDto));
    }

    @Override
    public List<Source> findAll() {
        return sourceRepository.findAll();
    }

    @Override
    public Optional<Source> updateById(Integer id, SourceDto sourceDto) {

        AtomicReference<Optional<Source>> atomicReference = new AtomicReference<>();

        sourceRepository.findById(id).ifPresentOrElse(foundSource -> {
            foundSource.setName(sourceDto.getName());
            foundSource.setUrl(sourceDto.getUrl());
            atomicReference.set(Optional.of(
                    sourceRepository.save(foundSource)));
        }, () -> atomicReference.set(Optional.empty()));

        return atomicReference.get();
    }

    @Override
    public Optional<Source> patchUpdateById(Integer id, SourceDto sourceDto) {
        AtomicReference<Optional<Source>> atomicReference = new AtomicReference<>();

        sourceRepository.findById(id).ifPresentOrElse(foundSource -> {
            if (StringUtils.hasText(sourceDto.getName())){
                foundSource.setName(sourceDto.getName());
            }
            if (StringUtils.hasText(sourceDto.getUrl())){
                foundSource.setUrl(sourceDto.getUrl());
            }
            if (StringUtils.hasText(sourceDto.getDescription())){
                foundSource.setDescription(sourceDto.getDescription());
            }

            atomicReference.set(Optional.of((sourceRepository.save(foundSource))));
        }, () -> {
            atomicReference.set(Optional.empty());
        });

        return atomicReference.get();
    }

    @Override
    public boolean deleteByid(Integer id) {

        if (sourceRepository.existsById(id)) {
            sourceRepository.deleteById(id);
            return true;
        }

        return false;
    }
}
