package by.architecture_map.belarus.service.impl

import by.architecture_map.belarus.entity.User
import by.architecture_map.belarus.properties.SendGridProperties
import by.architecture_map.belarus.service.EmailService
import com.sendgrid.Method
import com.sendgrid.Request
import com.sendgrid.Response
import com.sendgrid.SendGrid
import com.sendgrid.helpers.mail.Mail
import com.sendgrid.helpers.mail.objects.Content
import com.sendgrid.helpers.mail.objects.Email
import org.springframework.stereotype.Service

@Service
class EmailServiceImpl(
    private val sendGridProperties: SendGridProperties
) : EmailService {

    companion object {
        private const val SUBJECT = "Email Verification"
        private const val EMAIL_CONFIRMATION_PATH = "/register/confirm-email?token="
        private const val BASE_URL = "https://architecture-map.by"
    }

    override fun sendVerificationEmail(user: User, token: String) {
        val mail = buildEmail(user.username, token)
        sendEmail(mail)
    }

    private fun buildEmail(toEmail: String, token: String): Mail {
        val from = Email(sendGridProperties.fromEmail)
        val to = Email(toEmail)
        val confirmationUrl = "$BASE_URL$EMAIL_CONFIRMATION_PATH$token"
        val message = "Please confirm your email by clicking the following link: $confirmationUrl"
        val content = Content("text/plain", message)

        return Mail(from, SUBJECT, to, content)
    }

    private fun sendEmail(mail: Mail) {
        val sg = SendGrid(sendGridProperties.apiKey)
        val request = Request().apply {
            method = Method.POST
            endpoint = "/mail/send"
            body = mail.build()
        }

        val response = sg.api(request)
        handleResponse(response)
    }

    private fun handleResponse(response: Response) {
        if (response.statusCode != 202) {
            throw IllegalStateException("Failed to send email. Status code: ${response.statusCode}")
        }
    }
}
