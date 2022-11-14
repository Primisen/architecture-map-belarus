package by.architecture.map.service.impl;

import by.architecture.map.dto.SourceDto;
import by.architecture.map.entity.Source;
import by.architecture.map.exception.SourceException;
import by.architecture.map.mapper.SourceMapper;
import by.architecture.map.repository.SourceRepository;
import by.architecture.map.service.SourceService;
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

        if (sourceUpdates != null) {
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
