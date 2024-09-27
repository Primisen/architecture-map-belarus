package by.architecturemap.belarus.entity

import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
data class UserImage(

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User,

    /**
     * It is for filtering not thematic images. It can automate in the future
     */
        var approvedByAdmin: Boolean

) : ConstructionImage()
