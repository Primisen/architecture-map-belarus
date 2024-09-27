package by.architecturemap.belarus.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("sendgrid")
class SendGridProperties(
    val apiKey: String,
    val fromEmail: String
)
