package by.architecture_map.belarus.service.impl

import by.architecture_map.belarus.entity.EmailVerificationToken
import by.architecture_map.belarus.exception.NotFoundException
import by.architecture_map.belarus.repository.jpa.VerificationTokenRepository
import by.architecture_map.belarus.service.VerificationTokenService
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
