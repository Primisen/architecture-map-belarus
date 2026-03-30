package by.architecture_map.belarus.service.impl;

import by.architecture_map.belarus.dto.ConstructionDTO;
import by.architecture_map.belarus.entity.Construction;
import by.architecture_map.belarus.exception.NotFoundException;
import by.architecture_map.belarus.service.ConstructionService;
import by.architecture_map.belarus.repository.jpa.ConstructionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ConstructionServiceImpl implements ConstructionService {

    private final ConstructionRepository constructionRepository;
    private final ElasticsearchOperations elasticsearchOperations;

    @Override
    public Construction create(Construction construction) {
        return constructionRepository.save(construction);
    }

    @Override
    public List<Construction> findAll() {
        return constructionRepository.findAll();
    }

    @Override
    public Construction find(int id) {
        return constructionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Construction not found with id: " + id));
    }

    @Override
    public List<Construction> find(ConstructionDTO constructionDTO) {
        Criteria criteria = new Criteria();

        if (constructionDTO.getArchitecturalStyleId() != null && !constructionDTO.getArchitecturalStyleId().isEmpty())
            criteria = criteria.and("architecturalStyle.id").is(constructionDTO.getArchitecturalStyleId());
        if (constructionDTO.getRegion() != null && !constructionDTO.getRegion().isEmpty())
            criteria = criteria.and("address.region").is(constructionDTO.getRegion());
        if (constructionDTO.getDistrict() != null && !constructionDTO.getDistrict().isEmpty())
            criteria = criteria.and("address.district").is(constructionDTO.getDistrict());
        if (constructionDTO.getBuildingCenturyFrom() != null && !constructionDTO.getBuildingCenturyFrom().isEmpty())
            criteria = criteria.and("buildingCentury").greaterThanEqual(constructionDTO.getBuildingCenturyFrom());
        if (constructionDTO.getBuildingCenturyTo() != null && !constructionDTO.getBuildingCenturyTo().isEmpty())
            criteria = criteria.and("buildingCentury").lessThanEqual(constructionDTO.getBuildingCenturyTo());

        CriteriaQuery query = new CriteriaQuery(criteria);
        SearchHits<Construction> searchHits = elasticsearchOperations.search(query, Construction.class);
        return searchHits.stream()
                .map(hit -> hit.getContent())
                .collect(Collectors.toList());
    }

    @Override
    public Construction update(int id, Construction updatedConstruction) {
        return applyUpdates(id, existing -> {
            existing.setName(updatedConstruction.getName());
            existing.setDescription(updatedConstruction.getDescription());
            existing.setArchitecturalStyle(updatedConstruction.getArchitecturalStyle());
            existing.setAddress(updatedConstruction.getAddress());
            existing.setArchitects(updatedConstruction.getArchitects());
            existing.setBuildingDate(updatedConstruction.getBuildingDate());
            existing.setImages(updatedConstruction.getImages());
        });
    }

    @Override
    public Construction patchUpdate(int id, Construction updatedConstruction) {
        return applyUpdates(id, existing -> {
            if (updatedConstruction.getName() != null && !updatedConstruction.getName().isEmpty())
                existing.setName(updatedConstruction.getName());
            if (updatedConstruction.getAddress() != null)
                existing.setAddress(updatedConstruction.getAddress());
            if (updatedConstruction.getArchitects() != null && !updatedConstruction.getArchitects().isEmpty())
                existing.setArchitects(updatedConstruction.getArchitects());
            if (updatedConstruction.getArchitecturalStyle() != null)
                existing.setArchitecturalStyle(updatedConstruction.getArchitecturalStyle());
            if (updatedConstruction.getImages() != null && !updatedConstruction.getImages().isEmpty())
                existing.setImages(updatedConstruction.getImages());
            if (updatedConstruction.getDescription() != null && !updatedConstruction.getDescription().isEmpty())
                existing.setDescription(updatedConstruction.getDescription());
            if (updatedConstruction.getBuildingDate() != null && !updatedConstruction.getBuildingDate().isEmpty())
                existing.setBuildingDate(updatedConstruction.getBuildingDate());
        });
    }

    @Override
    public void delete(int id) {
        find(id);
        constructionRepository.deleteById(id);
    }

    private Construction applyUpdates(int id, Consumer<Construction> update) {
        Construction existing = find(id);
        update.accept(existing);
        return constructionRepository.save(existing);
    }

}
