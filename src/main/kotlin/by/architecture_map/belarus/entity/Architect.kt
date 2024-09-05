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
import jakarta.persistence.OneToOne

@Entity
data class Architect(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null,

        var name: String? = null,
        var middleName: String? = null,
        var surname: String? = null,
        var yearsOfLife: String? = null,
        var shortWorkDescription: String? = null,
        var biographicalArticle: String? = null,

        @OneToOne
        @JoinColumn(name = "portrait_image_id", referencedColumnName = "id")
        var portraitImage: Image? = null,

        @JsonIgnoreProperties("architects")
        @ManyToMany(cascade = [CascadeType.ALL])
        @JoinTable(
                name = "construction_architect",
                joinColumns = [JoinColumn(name = "architect_id")],
                inverseJoinColumns = [JoinColumn(name = "construction_id")]
        )
        var construction: MutableList<Construction>? = mutableListOf()
)