package by.architecturemap.belarus.entity

import jakarta.persistence.Entity

@Entity
data class Address(

    var locality: String? = null,
    var district: String? = null,
    var region: String,
    var street: String? = null,
    var houseNumber: String? = null

) : BaseEntity()
