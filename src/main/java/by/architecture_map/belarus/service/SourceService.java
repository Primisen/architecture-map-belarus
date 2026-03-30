package by.architecture_map.belarus.service;

import by.architecture_map.belarus.entity.Source;

import java.util.List;

public interface SourceService {

    Source create(Source source);

    Source find(int id);

    List<Source> findAll();

    Source update(int id, Source updatedSource);

    Source patchUpdate(int id, Source updatedSource);

    void delete(int id);
}
