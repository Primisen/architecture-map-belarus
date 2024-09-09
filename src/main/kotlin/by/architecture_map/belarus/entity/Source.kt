package by.architecture_map.belarus.entity

import jakarta.persistence.Entity

@Entity
data class Source(

    var name: String? = null,
    var url: String? = null,
    var description: String? = null

) : BaseEntity()
