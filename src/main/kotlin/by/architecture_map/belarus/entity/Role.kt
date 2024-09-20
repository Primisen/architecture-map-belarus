package by.architecture_map.belarus.entity

import jakarta.persistence.Entity

@Entity
data class Role(
    var name: String? = null
) : BaseEntity()
