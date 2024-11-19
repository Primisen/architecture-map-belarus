package by.architecturemap.belarus.service.impl

import by.architecturemap.belarus.entity.EmailVerificationToken
import by.architecturemap.belarus.exception.NotFoundException
import by.architecturemap.belarus.repository.jpa.EmailVerificationTokenRepository
import by.architecturemap.belarus.service.VerificationTokenService
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class EmailVerificationTokenServiceImpl(
    private val emailVerificationTokenRepository: EmailVerificationTokenRepository
) : VerificationTokenService {

    override fun save(token: EmailVerificationToken): EmailVerificationToken =
        emailVerificationTokenRepository.save(token)

    override fun getToken(token: String): EmailVerificationToken =
        find(token)
            .also { checkThatTokenNotExpired(it) }

    private fun checkThatTokenNotExpired(token: EmailVerificationToken) {
        token.expiryDate
            .let { check(!it.isBefore(LocalDateTime.now())) { "Token has expired" } }
    }

    private fun find(token: String): EmailVerificationToken =
        emailVerificationTokenRepository.findByToken(token)
            ?: throw NotFoundException("Invalid or expired token")
}
