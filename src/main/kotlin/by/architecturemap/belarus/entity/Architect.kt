package by.architecturemap.belarus.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToOne
import jakarta.validation.constraints.NotBlank

@Entity
data class Architect(

    @NotBlank(message = "Name may not be blank")
    var name: String,

    var middleName: String? = null,

    @NotBlank(message = "Surname may not be blank")
    var surname: String,

    /**
     * There are cases when there is no the exact date the life of architect,
     * but only an approximate time, for example: "end of the 17th century - 1756", so field has a string type
     */
    @NotBlank(message = "Years of life may not be blank")
    var yearsOfLife: String,

    @NotBlank(message = "Short work description may not be blank")
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
