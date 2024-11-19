package by.architecturemap.belarus.entity

import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.validation.constraints.NotNull

@Entity
data class UserImage(

    @NotNull(message = "User may not be null")
    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User,

    /**
     * It is for filtering not thematic images. It can automate in the future
     */
    @NotNull(message = "Approved by admin may not be null")
    var approvedByAdmin: Boolean = false

) : ConstructionImage()
