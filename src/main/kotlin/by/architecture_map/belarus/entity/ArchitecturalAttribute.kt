package by.architecture_map.belarus.entity

import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne

/**
 * Attributes that may be characteristic of the architectural
 * style, for example, pilasters, lancet windows, etc.
 */

@Entity
data class ArchitecturalAttribute(

    var name: String? = null,
    var description: String? = null,
    @OneToOne
    @JoinColumn(name = "demonstrative_image_id", referencedColumnName = "id")
    var demonstrativeImage: Image? = null

) : BaseEntity()
