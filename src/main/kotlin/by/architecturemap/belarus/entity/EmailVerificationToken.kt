package by.architecturemap.belarus.entity

import jakarta.persistence.Entity
import jakarta.persistence.OneToOne
import java.time.LocalDateTime
import java.util.*

@Entity
data class EmailVerificationToken(

    val token: String = UUID.randomUUID().toString(),

    @OneToOne
    val user: User,

    /**
     * Token expires after 24 hours
     */
    val expiryDate: LocalDateTime = LocalDateTime.now().plusDays(1)
) : BaseEntity()
