package by.architecture.map;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(title = "Architecture map API", version = "1.0"))
@SpringBootApplication
public class ArchitectureMapApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArchitectureMapApplication.class, args);
    }

}
