package by.architecture.map.service;

import by.architecture.map.dto.SourceDto;
import by.architecture.map.entity.Source;
import by.architecture.map.exception.SourceException;

import java.util.List;

public interface SourceService {

    List<Source> findAll();

    Source add(SourceDto sourceDto) throws SourceException;

    Source update(Integer id, SourceDto sourceUpdates) throws SourceException;

    void delete(Integer id) throws SourceException;

}
