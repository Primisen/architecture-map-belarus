package by.architecture_map.belarus.service;

import by.architecture_map.belarus.entity.Architect;

import java.util.List;

public interface ArchitectService {

    Architect create(Architect architect);

    Architect find(int id);

    List<Architect> findAll();
}
