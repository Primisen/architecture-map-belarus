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
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class ConstructionServiceImpl implements ConstructionService {

    private final ConstructionRepository constructionRepository;
    private final AddressRepository addressRepository;
    private final ConstructionMapper constructionMapper;
    private final AddressMapper addressMapper;
    private final ArchitecturalStyleRepository architecturalStyleRepository;

    @Override
    public Construction create(ConstructionDto constructionDto) {
//        Construction construction = constructionMapper.toConstruction(constructionDto);
//        addressRepository.save(construction.getAddress());

        //
//        construction.setArchitecturalStyle(
//                architecturalStyleRepository.
//                        findById(constructionDto.getArchitecturalStyleId()).get());
        return constructionRepository.save(constructionMapper.toConstruction(constructionDto));
    }

    @Override
    public List<Construction> findAll() {
        return constructionRepository.findAll();
    }

    @Override
    public Optional<Construction> findById(Integer id) {
        return constructionRepository.findById(id);
    }

    @Override
    public Optional<Construction> updateBuId(Integer id, ConstructionDto constructionDto) {

        AtomicReference<Optional<Construction>> atomicReference = new AtomicReference<>();

        constructionRepository.findById(id).ifPresentOrElse(foundConstruction -> {
            foundConstruction.setName(constructionDto.getName());
            foundConstruction.setDescription(constructionDto.getDescription());
            atomicReference.set(Optional.of(
                    constructionRepository.save(foundConstruction)));
        }, () -> atomicReference.set(Optional.empty()));

        return atomicReference.get();
    }

    @Override
    public Optional<Construction> patchUpdateBuId(Integer id, ConstructionDto constructionDto) {
        AtomicReference<Optional<Construction>> atomicReference = new AtomicReference<>();

        constructionRepository.findById(id).ifPresentOrElse(foundConstruction -> {
            if (constructionDto.getArchitecturalStyleId() != null){
                foundConstruction.setArchitecturalStyle(architecturalStyleRepository.getReferenceById(constructionDto.getArchitecturalStyleId()));
            }
            if (StringUtils.hasText(constructionDto.getName())){
                foundConstruction.setName(constructionDto.getName());
            }
            atomicReference.set(Optional.of((constructionRepository.save(foundConstruction))));
        }, () -> {
            atomicReference.set(Optional.empty());
        });

        return atomicReference.get();
    }

    @Override
    public boolean deleteById(Integer id) {
        if (constructionRepository.existsById(id)) {
            constructionRepository.deleteById(id);
            return true;
        }

        return false;
    }
}
