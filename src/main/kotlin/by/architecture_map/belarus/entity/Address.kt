package by.architecture_map.belarus.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Address(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null,
        var locality: String? = null,
        var district: String? = null,
        var region: String? = null,
        var street: String? = null,
        var houseNumber: String? = null
)