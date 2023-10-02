package com.architecture_map.belarus.service.impl;

import com.architecture_map.belarus.dto.SourceDto;
import com.architecture_map.belarus.entity.Source;
import com.architecture_map.belarus.exception.SourceException;
import com.architecture_map.belarus.mapper.SourceMapper;
import com.architecture_map.belarus.repository.SourceRepository;
import com.architecture_map.belarus.service.SourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SourceServiceImpl implements SourceService {

    @Autowired
    private SourceRepository sourceRepository;

    @Autowired
    private SourceMapper sourceMapper;


    @Override
    public List<Source> findAll() {
        return sourceRepository.findAll();
    }

    @Override
    public Source add(SourceDto sourceDto) throws SourceException {

        if (sourceNotExists(sourceDto)) {
            return save(sourceDto);

        } else {
            throw new SourceException("Source is exists.");
        }
    }

    @Override
    public Source update(Integer id, SourceDto sourceUpdates) throws SourceException {

        Source source = findById(id);

        updateName(source, sourceUpdates);
        updateUrl(source, sourceUpdates);

        return sourceRepository.save(source);
    }

    @Override
    public void delete(Integer id) throws SourceException {

        if (sourceIsExists(id)) {
            sourceRepository.deleteById(id);

        } else {
            throw new SourceException("Source not exists.");
        }
    }

    private void updateUrl(Source source, SourceDto sourceUpdates) {

        if (sourceUpdates.getUrl() != null) {
            source.setUrl(sourceUpdates.getUrl());
        }
    }

    private void updateName(Source source, SourceDto sourceUpdates) {

        if (sourceUpdates.getName() != null) {
            source.setName(sourceUpdates.getName());
        }
    }

    private Source findById(Integer id) throws SourceException {

        return sourceRepository.findById(id)
                .orElseThrow(() -> new SourceException("Source with id = " + id + " not exists."));
    }

    private Source save(SourceDto sourceDto) {
        return sourceRepository.save(sourceMapper.toSource(sourceDto));
    }

    private boolean sourceNotExists(SourceDto sourceDto) {
        return !sourceIsExists(sourceDto);
    }

    private boolean sourceIsExists(SourceDto sourceDto) {
        return sourceRepository.existsByUrl(sourceDto.getUrl());
    }

    private boolean sourceIsExists(Integer id) {
        return sourceRepository.existsById(id);
    }
}
