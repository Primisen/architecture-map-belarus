package by.architecturemap.belarus.entity

import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.validation.constraints.NotBlank

/**
 * Attributes that may be characteristic of the architectural
 * style, for example, pilasters, lancet windows, etc.
 */

@Entity
data class ArchitecturalAttribute(

    @NotBlank(message = "Name may not be blank")
    var name: String,

    var description: String? = null,

    @OneToOne
    @JoinColumn(name = "demonstrative_image_id", referencedColumnName = "id")
    var demonstrativeImage: Image? = null

) : BaseEntity()
