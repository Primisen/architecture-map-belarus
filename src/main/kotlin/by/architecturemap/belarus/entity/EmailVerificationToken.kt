package by.architecturemap.belarus.entity

import jakarta.persistence.Entity
import jakarta.persistence.OneToOne
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime
import java.util.UUID

@Entity
data class EmailVerificationToken(

    @NotNull(message = "Token may not be null")
    val token: String = UUID.randomUUID().toString(),

    @NotNull(message = "User may not be null")
    @OneToOne
    val user: User,

    /**
     * Token expires after 24 hours
     */
    @NotNull(message = "Expiry date may not be null")
    val expiryDate: LocalDateTime = LocalDateTime.now().plusDays(1)
) : BaseEntity()
