package by.architecture_map.belarus.repository.elasticsearch;

import by.architecture_map.belarus.entity.Construction;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConstructionElasticsearchRepository extends ElasticsearchRepository<Construction, Integer> {
}