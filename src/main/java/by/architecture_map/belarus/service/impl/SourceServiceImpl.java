package by.architecture_map.belarus.service.impl;

import by.architecture_map.belarus.entity.Source;
import by.architecture_map.belarus.exception.NotFoundException;
import by.architecture_map.belarus.repository.jpa.SourceRepository;
import by.architecture_map.belarus.service.SourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class SourceServiceImpl implements SourceService {

    private final SourceRepository sourceRepository;

    @Override
    public Source create(Source source) {
        return sourceRepository.save(source);
    }

    @Override
    public List<Source> findAll() {
        return sourceRepository.findAll();
    }

    @Override
    public Source find(int id) {
        return sourceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Source not found with id: " + id));
    }

    @Override
    public Source update(int id, Source updatedSource) {
        return applyUpdates(id, existing -> {
            existing.setName(updatedSource.getName());
            existing.setUrl(updatedSource.getUrl());
            existing.setDescription(updatedSource.getDescription());
        });
    }

    @Override
    public Source patchUpdate(int id, Source updatedSource) {
        return applyUpdates(id, existing -> {
            if (updatedSource.getName() != null && !updatedSource.getName().isEmpty())
                existing.setName(updatedSource.getName());
            if (updatedSource.getUrl() == null || updatedSource.getUrl().isEmpty())
                existing.setUrl(updatedSource.getUrl());
            if (updatedSource.getDescription() != null && !updatedSource.getDescription().isEmpty())
                existing.setDescription(updatedSource.getDescription());
        });
    }

    @Override
    public void delete(int id) {
        find(id);
        sourceRepository.deleteById(id);
    }

    private Source applyUpdates(int id, Consumer<Source> update) {
        Source existing = find(id);
        update.accept(existing);
        return sourceRepository.save(existing);
    }

}
