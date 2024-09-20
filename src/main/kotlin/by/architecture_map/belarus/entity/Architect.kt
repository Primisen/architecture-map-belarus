package by.architecture_map.belarus.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToOne

@Entity
data class Architect(

    var name: String? = null,
    var middleName: String? = null,
    var surname: String? = null,

    /**
     * There are cases when there is no the exact date the life of architect,
     * but only an approximate time, for example: "end of the 17th century - 1756", so field has a string type
     */
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
    var construction: List<Construction>? = listOf()

) : BaseEntity()
