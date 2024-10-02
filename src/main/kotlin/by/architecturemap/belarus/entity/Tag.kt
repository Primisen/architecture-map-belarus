package by.architecturemap.belarus.entity

import jakarta.persistence.Entity
import jakarta.validation.constraints.NotBlank

@Entity
class Tag(

    @NotBlank(message = "Name may not be blank")
    var name: String

) : BaseEntity()
