package by.architecturemap.belarus.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.springframework.data.elasticsearch.annotations.Document

/**
 * The class means to architectural structures such as buildings, separate arches,
 * gates, columns (for example, the column in honor of the constitution of 1791)
 */
@Entity
@Document(indexName = "construction")
data class Construction(

    @NotBlank(message = "Name may not be blank")
    var name: String,

    /**
     * There are cases when there is no the exact date the construction was built,
     * but only an approximate time, for example: "the second half of the 19th century",
     * so field has a string type
     */
    var buildingDate: String? = null,

    /**
     * BuildingDate has difficult type for searching, because it needs complex parsing,
     * which in turn will reduce productivity. So, much easy look for construction by building century.
     */
    var buildingCentury: Short? = null,

    @NotNull(message = "Address may not be null")
    @OneToOne(cascade = [CascadeType.PERSIST])
    @JoinColumn(name = "address_id")
    var address: Address,

    @NotNull(message = "Architectural style may not be null")
    @ManyToOne(cascade = [CascadeType.MERGE])
    @JoinColumn(name = "architectural_style_id")
    var architecturalStyle: ArchitecturalStyle,

    @JsonIgnoreProperties("constructions")
    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "construction_architect",
        joinColumns = [JoinColumn(name = "construction_id")],
        inverseJoinColumns = [JoinColumn(name = "architect_id")]
    )
    var architects: List<Architect>? = listOf(),

    var description: String? = null,

    @NotEmpty(message = "Construction image may not be empty")
    @JsonIgnoreProperties("construction")
    @OneToMany(mappedBy = "construction", cascade = [CascadeType.ALL], targetEntity = ConstructionImage::class)
    var images: List<ConstructionImage> = listOf()

) : BaseEntity()
