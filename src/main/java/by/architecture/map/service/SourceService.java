package by.architecture.map.service;

import by.architecture.map.dto.SourceDto;
import by.architecture.map.entity.Source;
import by.architecture.map.exception.SourceException;

import java.util.List;

public interface SourceService {
    void add(SourceDto sourceDto) throws SourceException;

    List<Source> findAll();

    void delete(Integer id) throws SourceException;

    void update(Integer id, SourceDto sourceUpdates) throws SourceException;
}
