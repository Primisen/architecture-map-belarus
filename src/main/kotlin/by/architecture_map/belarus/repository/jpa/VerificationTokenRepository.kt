package by.architecture_map.belarus.repository.jpa

import by.architecture_map.belarus.entity.EmailVerificationToken
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VerificationTokenRepository : JpaRepository<EmailVerificationToken, Int> {
    fun findByToken(token: String): EmailVerificationToken?
}
