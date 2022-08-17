package by.architecture.map.configuration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "keycloak.server")
@Getter
@Setter(AccessLevel.PUBLIC)
public class KeycloakServerProperties {

    String contextPath = "/auth";
    String realmImportFile = "baeldung-realm.json";//????
    AdminUser adminUser = new AdminUser();

    @Getter
    @Setter(AccessLevel.PUBLIC)
    public static class AdminUser {
        String username = "admin";
        String password = "admin";
    }
}
