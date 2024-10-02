package by.architecturemap.belarus.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToOne
import jakarta.validation.constraints.NotBlank

@Entity
data class ArchitecturalStyle(

    @NotBlank(message = "Name may not be blank")
    var name: String,

    var description: String? = null,

    @ManyToMany
    @JoinTable(
        name = "architectural_style_architectural_attribute",
        joinColumns = [JoinColumn(name = "architectural_style_id")],
        inverseJoinColumns = [JoinColumn(name = "architectural_attribute_id")]
    )
    var attributes: List<ArchitecturalAttribute>? = listOf(),

    @JsonIgnoreProperties("construction")
    @OneToOne
    @JoinColumn(name = "demonstrative_image_id", referencedColumnName = "id")
    var demonstrativeImage: Image? = null,

    var shortDescription: String? = null,
    var yearsActive: String? = null

) : BaseEntity()
