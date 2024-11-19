package by.architecturemap.belarus.service.impl

import by.architecturemap.belarus.entity.User
import by.architecturemap.belarus.properties.SendGridProperties
import com.sendgrid.Request
import com.sendgrid.Response
import com.sendgrid.SendGrid
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkConstructor
import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.http.HttpStatus

class EmailServiceImplTest {

    private lateinit var sendGridProperties: SendGridProperties
    private lateinit var emailService: EmailServiceImpl

    private val user = User(username = "test@example.com", password = "12345678".toCharArray())
    private val token = "hdgftr6583sdf389oewe"

    @BeforeEach
    fun setUp() {
        sendGridProperties = mockk {
            every { apiKey } returns "sendgrid-api-key"
            every { fromEmail } returns "test@example.com"
        }
        emailService = spyk(EmailServiceImpl(sendGridProperties))
    }

    @Test
    fun givenValidRequest_whenSendVerificationTokenToEmail_thenSendEmailUsingSendGrid() {
        // arrange
        val response = Response(HttpStatus.ACCEPTED.value(), "{}", emptyMap())

        mockkConstructor(SendGrid::class)
        every { anyConstructed<SendGrid>().api(any<Request>()) } returns response

        // act
        emailService.sendVerificationTokenToEmail(user, token)

        // assert
        verify(exactly = 1) { anyConstructed<SendGrid>().api(any<Request>()) }
    }

    @Test
    fun givenFailedResponse_whenSendVerificationTokenToEmail_thenThrowIllegalStateException() {
        // arrange
        val failedResponse = Response(HttpStatus.BAD_REQUEST.value(), "{}", emptyMap())

        mockkConstructor(SendGrid::class)
        every { anyConstructed<SendGrid>().api(any<Request>()) } returns failedResponse

        // act & assert
        assertThrows<IllegalStateException> { emailService.sendVerificationTokenToEmail(user, token) }
    }
}
