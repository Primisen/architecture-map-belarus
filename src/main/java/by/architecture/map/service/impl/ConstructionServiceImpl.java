package by.architecture.map.service.impl;

import by.architecture.map.dto.ConstructionDto;
import by.architecture.map.entity.Construction;
import by.architecture.map.mapper.ConstructionMapper;
import by.architecture.map.repository.ConstructionRepository;
import by.architecture.map.service.ConstructionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConstructionServiceImpl implements ConstructionService {

    @Autowired
    private ConstructionRepository constructionRepository;

    @Autowired
    private ConstructionMapper constructionMapper;

    @Override
    public List<Construction> findAll() {
        return constructionRepository.findAll();
    }

    @Override
    public void add(ConstructionDto constructionDto) {
        Construction construction = constructionMapper.constructionDtoToConstruction(constructionDto);
        constructionRepository.save(construction);
    }

    @Override
    public void update(UUID idOfOldConstruction, ConstructionDto updatedConstruction) {

        Construction oldConstruction = constructionRepository.findById(idOfOldConstruction).get();
        oldConstruction.setAddress(updatedConstruction.getAddress());
        oldConstruction.setName(updatedConstruction.getName());
    }

    @Override
    public void delete(UUID id) {
        constructionRepository.deleteById(id);
    }
}
