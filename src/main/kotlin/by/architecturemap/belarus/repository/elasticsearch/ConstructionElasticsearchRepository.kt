package by.architecturemap.belarus.repository.elasticsearch

import by.architecturemap.belarus.entity.Construction
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import org.springframework.stereotype.Repository

@Repository
interface ConstructionElasticsearchRepository : ElasticsearchRepository<Construction, Int>
