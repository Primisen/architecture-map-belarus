package by.architecturemap.belarus.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories

@Configuration
@EnableElasticsearchRepositories("by.architecturemap.belarus.repository.elasticsearch")
open class ElasticsearchConfiguration
