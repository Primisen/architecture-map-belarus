package by.architecturemap.belarus.entity

import jakarta.persistence.Entity
import jakarta.validation.constraints.NotBlank

@Entity
data class Source(

    @NotBlank(message = "Name may not be blank")
    var name: String,

    @NotBlank(message = "Url may not be blank")
    var url: String,

    var description: String? = null

) : BaseEntity()
