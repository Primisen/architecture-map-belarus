package by.architecturemap.belarus.entity

import jakarta.persistence.Entity
import jakarta.validation.constraints.NotBlank

@Entity
data class Address(

    var locality: String? = null,
    var district: String? = null,

    @NotBlank(message = "Region may not be blank")
    var region: String,

    var street: String? = null,
    var houseNumber: String? = null

) : BaseEntity()
