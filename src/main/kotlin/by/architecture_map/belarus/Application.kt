package by.architecture_map.belarus

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@OpenAPIDefinition(info = Info(title = "Architecture map API", version = "1.0"))
@SpringBootApplication
@EnableJpaRepositories("by.architecture_map.belarus.repository.jpa")
@EnableElasticsearchRepositories("by.architecture_map.belarus.repository.elasticsearch")
open class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}