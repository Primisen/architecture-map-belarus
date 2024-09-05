package by.architecture_map.belarus.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne

@Entity
data class Construction(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null,
        var name: String? = null,
        var buildingTime: String? = null,

        @OneToOne(cascade = [CascadeType.PERSIST])
        @JoinColumn(name = "address_id", nullable = false)
        var address: Address? = null,

        @ManyToOne(cascade = [CascadeType.MERGE])
        @JoinColumn(name = "architectural_style_id")
        var architecturalStyle: ArchitecturalStyle? = null,

        @JsonIgnoreProperties("constructions")
        @ManyToMany(cascade = [CascadeType.ALL])
        @JoinTable(
                name = "construction_architect",
                joinColumns = [JoinColumn(name = "construction_id")],
                inverseJoinColumns = [JoinColumn(name = "architect_id")]
        )
        var architects: MutableList<Architect>? = mutableListOf(),

        var description: String? = null,

        @JsonIgnoreProperties("construction")
        @OneToMany(mappedBy = "construction")
        var images: MutableList<ConstructionImage>? = mutableListOf()
)