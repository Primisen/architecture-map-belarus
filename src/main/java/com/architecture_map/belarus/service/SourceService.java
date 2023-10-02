package com.architecture_map.belarus.service;

import com.architecture_map.belarus.dto.SourceDto;
import com.architecture_map.belarus.entity.Source;
import com.architecture_map.belarus.exception.SourceException;

import java.util.List;

public interface SourceService {

    List<Source> findAll();

    Source add(SourceDto sourceDto) throws SourceException;

    Source update(Integer id, SourceDto sourceUpdates) throws SourceException;

    void delete(Integer id) throws SourceException;

}
