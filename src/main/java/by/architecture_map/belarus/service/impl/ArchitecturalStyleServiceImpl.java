package by.architecture_map.belarus.service.impl;

import by.architecture_map.belarus.entity.ArchitecturalStyle;
import by.architecture_map.belarus.exception.NotFoundException;
import by.architecture_map.belarus.service.ArchitecturalStyleService;
import by.architecture_map.belarus.service.ImageService;
import by.architecture_map.belarus.repository.jpa.ArchitecturalStyleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArchitecturalStyleServiceImpl implements ArchitecturalStyleService {

    private final ArchitecturalStyleRepository architecturalStyleRepository;
    private final ImageService imageService;

    @Override
    public ArchitecturalStyle create(ArchitecturalStyle architecturalStyle) {
        return architecturalStyleRepository.save(architecturalStyle);
    }

    @Override
    public List<ArchitecturalStyle> findAll() {
        return architecturalStyleRepository.findAll();
    }

    @Override
    public ArchitecturalStyle find(int id) {
        return architecturalStyleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Architectural style not found with id: " + id));
    }

    @Override
    public ArchitecturalStyle update(int id, ArchitecturalStyle updatedArchitecturalStyle) {
        return applyUpdates(id, existing -> {
            existing.setName(updatedArchitecturalStyle.getName());
            existing.setAttributes(updatedArchitecturalStyle.getAttributes());
            existing.setDescription(updatedArchitecturalStyle.getDescription());
            existing.setShortDescription(updatedArchitecturalStyle.getShortDescription());
            existing.setDemonstrativeImage(updatedArchitecturalStyle.getDemonstrativeImage());
            existing.setYearsActive(updatedArchitecturalStyle.getYearsActive());
        });
    }

    @Override
    public ArchitecturalStyle patchUpdate(int id, ArchitecturalStyle updatedArchitecturalStyle) {
        return applyUpdates(id, existing -> {
            if (updatedArchitecturalStyle.getName() != null && !updatedArchitecturalStyle.getName().isEmpty())
                existing.setName(updatedArchitecturalStyle.getName());
            if (updatedArchitecturalStyle.getAttributes() != null && !updatedArchitecturalStyle.getAttributes().isEmpty())
                existing.setAttributes(updatedArchitecturalStyle.getAttributes());
            if (updatedArchitecturalStyle.getDescription() != null && !updatedArchitecturalStyle.getDescription().isEmpty())
                existing.setDescription(updatedArchitecturalStyle.getDescription());
            if (updatedArchitecturalStyle.getShortDescription() != null && !updatedArchitecturalStyle.getShortDescription().isEmpty())
                existing.setShortDescription(updatedArchitecturalStyle.getShortDescription());
            if (updatedArchitecturalStyle.getDemonstrativeImage() != null) {
                Integer imageId = updatedArchitecturalStyle.getDemonstrativeImage().getId();
                if (imageId != null)
                    existing.setDemonstrativeImage(imageService.find(imageId));
            }
            if (updatedArchitecturalStyle.getYearsActive() != null && !updatedArchitecturalStyle.getYearsActive().isEmpty())
                existing.setYearsActive(updatedArchitecturalStyle.getYearsActive());
        });
    }

    @Override
    public void delete(int id) {
        find(id);
        architecturalStyleRepository.deleteById(id);
    }

    private ArchitecturalStyle applyUpdates(int id, java.util.function.Consumer<ArchitecturalStyle> update) {
        ArchitecturalStyle existing = find(id);
        update.accept(existing);
        return architecturalStyleRepository.save(existing);
    }

}
