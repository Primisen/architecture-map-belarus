package by.architecture.map;

//import by.architecture.map.configuration.KeycloakServerProperties;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.api.gax.rpc.ApiException;
import com.google.photos.library.v1.PhotosLibraryClient;
import com.google.photos.library.v1.PhotosLibrarySettings;
import com.google.photos.types.proto.Album;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

import static org.keycloak.models.PasswordPolicy.build;

@OpenAPIDefinition(info = @Info(title = "Architecture map API", version = "1.0"))
@SpringBootApplication(exclude = LiquibaseAutoConfiguration.class)
//@EnableConfigurationProperties(KeycloakServerProperties.class)
public class ArchitectureMapApplication {

    private static final Logger LOG = LoggerFactory.getLogger(ArchitectureMapApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ArchitectureMapApplication.class, args);
    }

}
