package by.architecture_map.belarus.configuration

import by.architecture_map.belarus.properties.SendGridProperties
import com.sendgrid.SendGrid
import com.sendgrid.helpers.mail.objects.Email
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(SendGridProperties::class)
open class SendGridConfiguration(
    private val sendGridProperties: SendGridProperties
) {

    @Bean
    open fun sendGrid(): SendGrid = SendGrid(sendGridProperties.apiKey)

    @Bean
    open fun fromEmail(): Email = Email(sendGridProperties.fromEmail)
}
