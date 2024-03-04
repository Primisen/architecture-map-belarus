package com.architecture_map.belarus.service.impl;

import com.architecture_map.belarus.dto.ConstructionDto;
import com.architecture_map.belarus.entity.Construction;
import com.architecture_map.belarus.mapper.AddressMapper;
import com.architecture_map.belarus.mapper.ConstructionMapper;
import com.architecture_map.belarus.repository.AddressRepository;
import com.architecture_map.belarus.repository.ArchitecturalStyleRepository;
import com.architecture_map.belarus.repository.ConstructionRepository;
import com.architecture_map.belarus.service.ConstructionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Autowired
    private ArchitecturalStyleRepository architecturalStyleRepository;

    @Override
    public List<Construction> findAll() {
        return constructionRepository.findAll();
    }

    @Override
    public Construction findById(Integer id) {

        Construction construction = constructionRepository.findById(id).get();
        return construction;
    }

    @Override
    public void add(ConstructionDto constructionDto) {
        Construction construction = constructionMapper.toConstruction(constructionDto);
        addressRepository.save(construction.getAddress());

        //
        construction.setArchitecturalStyle(
                architecturalStyleRepository.
                        findById(constructionDto.getArchitecturalStyleId()).get());
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

    @Override
    public Optional<Construction> partialUpdate(Integer id, ConstructionDto construction) {
        return Optional.empty();
    }

}
