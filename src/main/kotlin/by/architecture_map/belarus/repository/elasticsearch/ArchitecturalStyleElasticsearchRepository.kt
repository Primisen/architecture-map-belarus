package by.architecture_map.belarus.repository.elasticsearch

import by.architecture_map.belarus.entity.ArchitecturalStyle
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import org.springframework.stereotype.Repository

@Repository
interface ArchitecturalStyleElasticsearchRepository: ElasticsearchRepository<ArchitecturalStyle, Int> {
}