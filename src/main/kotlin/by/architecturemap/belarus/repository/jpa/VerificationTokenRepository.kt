package by.architecturemap.belarus.repository.jpa

import by.architecturemap.belarus.entity.EmailVerificationToken
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VerificationTokenRepository : JpaRepository<EmailVerificationToken, Int> {
    fun findByToken(token: String): EmailVerificationToken?
}
