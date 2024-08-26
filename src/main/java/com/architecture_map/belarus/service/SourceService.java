package com.architecture_map.belarus.service;

import com.architecture_map.belarus.dto.SourceDto;
import com.architecture_map.belarus.entity.Source;

import java.util.List;
import java.util.Optional;

public interface SourceService {

    Source create(SourceDto sourceDto);

    List<Source> findAll();

    Optional<Source> updateById(Integer id, SourceDto sourceUpdates);

    Optional<Source> patchUpdateById(Integer id, SourceDto sourceDto);

    boolean deleteByid(Integer id);
}
