package by.architecture.map.service.impl;

import by.architecture.map.dto.ConstructionDto;
import by.architecture.map.entity.Construction;
import by.architecture.map.mapper.AddressMapper;
import by.architecture.map.mapper.ConstructionMapper;
import by.architecture.map.repository.AddressRepository;
import by.architecture.map.repository.ConstructionRepository;
import by.architecture.map.service.ConstructionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConstructionServiceImpl implements ConstructionService {

    @Autowired
    private ConstructionRepository constructionRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ConstructionMapper constructionMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public List<Construction> findAll() {
        return constructionRepository.findAll();
    }

    @Override
    public Construction findById(Integer id) {
        return constructionRepository.findById(id).get();//
    }

    @Override
    public void add(ConstructionDto constructionDto) {
        Construction construction = constructionMapper.toConstruction(constructionDto);
        addressRepository.save(construction.getAddress());
        constructionRepository.save(construction);
    }

    @Override
    public void update(Integer idOfOldConstruction, ConstructionDto updatedConstruction) {

        Construction oldConstruction = constructionRepository.findById(idOfOldConstruction).get();
        oldConstruction.setAddress(addressMapper.toAddress(updatedConstruction.getAddress()));
        oldConstruction.setName(updatedConstruction.getName());
    }

    @Override
    public void delete(Integer id) {
        constructionRepository.deleteById(id);
    }
}
