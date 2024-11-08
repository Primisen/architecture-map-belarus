package by.architecturemap.belarus.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("sendgrid")
data class SendGridProperties(
    var apiKey: String,
    var fromEmail: String
)
