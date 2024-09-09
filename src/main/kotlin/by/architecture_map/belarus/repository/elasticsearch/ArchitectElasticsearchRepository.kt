package by.architecture_map.belarus.repository.elasticsearch

import by.architecture_map.belarus.entity.Architect
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import org.springframework.stereotype.Repository

@Repository
interface ArchitectElasticsearchRepository: ElasticsearchRepository<Architect, Int> {
}