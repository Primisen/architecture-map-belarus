package by.architecture_map.belarus.service;

import by.architecture_map.belarus.entity.ArchitecturalStyle;

import java.util.List;

public interface ArchitecturalStyleService {

    ArchitecturalStyle create(ArchitecturalStyle architecturalStyle);

    List<ArchitecturalStyle> findAll();

    ArchitecturalStyle find(int id);

    ArchitecturalStyle update(int id, ArchitecturalStyle updatedArchitecturalStyle);

    ArchitecturalStyle patchUpdate(int id, ArchitecturalStyle updatedArchitecturalStyle);

    void delete(int id);
}
