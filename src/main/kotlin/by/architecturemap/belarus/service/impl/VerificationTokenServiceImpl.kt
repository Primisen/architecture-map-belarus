package by.architecturemap.belarus.service.impl

import by.architecturemap.belarus.entity.EmailVerificationToken
import by.architecturemap.belarus.exception.NotFoundException
import by.architecturemap.belarus.repository.jpa.VerificationTokenRepository
import by.architecturemap.belarus.service.VerificationTokenService
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class VerificationTokenServiceImpl(
    private val verificationTokenRepository: VerificationTokenRepository
) : VerificationTokenService {

    override fun save(token: EmailVerificationToken): EmailVerificationToken =
        verificationTokenRepository.save(token)

    override fun find(token: String): EmailVerificationToken =
        verificationTokenRepository.findByToken(token)
            ?: throw NotFoundException("Invalid or expired token")

    override fun checkThatTokenExistsAndNotExpired(token: String): EmailVerificationToken =
        find(token)
            .also { checkThatTokenNotExpired(it) }

    private fun checkThatTokenNotExpired(token: EmailVerificationToken) {
        token.expiryDate
            .let { check(!it.isBefore(LocalDateTime.now())) { "Token has expired" } }
    }
}
