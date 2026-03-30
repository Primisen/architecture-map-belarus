package by.architecture_map.belarus.service.impl;

import by.architecture_map.belarus.entity.Architect;
import by.architecture_map.belarus.exception.NotFoundException;
import by.architecture_map.belarus.repository.jpa.ArchitectRepository;
import by.architecture_map.belarus.service.ArchitectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArchitectServiceImpl implements ArchitectService {

    private final ArchitectRepository architectRepository;

    @Override
    public Architect create(Architect architect) {
        return architectRepository.save(architect);
    }

    @Override
    public Architect find(int id) {
        return architectRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Architect not found with id: " + id));
    }

    @Override
    public List<Architect> findAll() {
        return architectRepository.findAll();
    }
}
