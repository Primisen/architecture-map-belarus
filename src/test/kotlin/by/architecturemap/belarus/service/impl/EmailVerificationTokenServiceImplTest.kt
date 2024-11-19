package by.architecturemap.belarus.service.impl

import by.architecturemap.belarus.entity.EmailVerificationToken
import by.architecturemap.belarus.entity.User
import by.architecturemap.belarus.exception.NotFoundException
import by.architecturemap.belarus.repository.jpa.EmailVerificationTokenRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDateTime

class EmailVerificationTokenServiceImplTest {

    private val emailVerificationTokenRepository: EmailVerificationTokenRepository = mockk()
    private val verificationTokenService = EmailVerificationTokenServiceImpl(emailVerificationTokenRepository)

    @Test
    fun givenValidToken_whenSaveToken_thenReturnSavedToken() {
        // arrange
        val token = "jdhgf"
        val user = User(username = "test@test.com", password = "12345678".toCharArray())
        val emailVerificationToken = EmailVerificationToken(token, user)
        every { emailVerificationTokenRepository.save(emailVerificationToken) } returns emailVerificationToken

        // act
        val actual = verificationTokenService.save(emailVerificationToken)

        // assert
        assertEquals(emailVerificationToken, actual)
    }

    @Test
    fun givenValidToken_whenGetToken_thenReturnEmailVerificationToken() {
        // arrange
        val token = "jdhgf"
        val user = User(username = "test@test.com", password = "12345678".toCharArray())
        val emailVerificationToken = EmailVerificationToken(token, user)
        every { emailVerificationTokenRepository.findByToken(token) } returns emailVerificationToken

        // act
        val actual = verificationTokenService.getToken(token)

        // assert
        assertEquals(emailVerificationToken, actual)
    }

    @Test
    fun givenNonExistentToken_whenGetToken_thenThrowNotFoundException() {
        // arrange
        val token = "jdhgf"
        every { emailVerificationTokenRepository.findByToken(token) } returns null

        // act & assert
        assertThrows<NotFoundException> { verificationTokenService.getToken(token) }
    }

    @Test
    fun givenExpiredToken_whenGetToken_thenThrowIllegalStateException() {
        // arrange
        val token = "jdhgf"
        val user = User(username = "test@test.com", password = "12345678".toCharArray())
        val emailVerificationToken = EmailVerificationToken(token, user, LocalDateTime.MIN)
        every { emailVerificationTokenRepository.findByToken(token) } returns emailVerificationToken

        // act & assert
        assertThrows<IllegalStateException> { verificationTokenService.getToken(token) }
    }
}
