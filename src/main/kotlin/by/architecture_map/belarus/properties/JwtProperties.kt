package by.architecture_map.belarus.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("jwt")
class JwtProperties(
    val key: String,

    /**
     * Expiration time in milliseconds
     */
    val accessTokenExpiration: Long,
)
