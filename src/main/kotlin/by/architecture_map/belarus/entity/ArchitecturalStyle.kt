package by.architecture_map.belarus.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToOne

@Entity
data class ArchitecturalStyle(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null,
        var name: String? = null,
        var description: String? = null,

        @ManyToMany
        @JoinTable(
                name = "architectural_style_architectural_attribute",
                joinColumns = [JoinColumn(name = "architectural_style_id")],
                inverseJoinColumns = [JoinColumn(name = "architectural_attribute_id")]
        )
        var attributes: MutableList<ArchitecturalAttribute>? = mutableListOf(),

        @JsonIgnoreProperties("construction")
        @OneToOne
        @JoinColumn(name = "demonstrative_image_id", referencedColumnName = "id")
        var demonstrativeImage: Image? = null,

        var shortDescription: String? = null,
        var yearsActive: String? = null
)