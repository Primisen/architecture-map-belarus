package by.architecture_map.belarus;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@OpenAPIDefinition(info = @Info(title = "Architecture map API", version = "1.0"))
@SpringBootApplication
@EnableJpaRepositories("by.architecture_map.belarus.repository.jpa")
//@EnableElasticsearchRepositories("by.architecture_map.belarus.repository.elasticsearch")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
