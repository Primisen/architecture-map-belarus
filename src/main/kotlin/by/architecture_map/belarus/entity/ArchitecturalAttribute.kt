package by.architecture_map.belarus.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne

@Entity
data class ArchitecturalAttribute(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null,
        var name: String? = null,
        var description: String? = null,
        @OneToOne
        @JoinColumn(name = "demonstrative_image_id", referencedColumnName = "id")
        var demonstrativeImage: Image? = null
)