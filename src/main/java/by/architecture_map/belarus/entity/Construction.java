package by.architecture_map.belarus.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
//import org.springframework.data.elasticsearch.annotations.Document

/**
 * The class means to architectural structures such as buildings, separate arches,
 * gates, columns (for example, the column in honor of the constitution of 1791)
 */
@Entity
//@Document(indexName = "construction")
@Data
@EqualsAndHashCode(callSuper = true)
public class Construction extends BaseEntity {

    private String name;

    /**
     * There are cases when there is no the exact date the construction was built,
     * but only an approximate time, for example: "the second half of the 19th century",
     * so field has a string type
     */
    private String buildingDate;

    /**
     * BuildingDate has difficult type for searching, because it needs complex parsing,
     * which in turn will reduce productivity. So, much easy look for construction by building century.
     */
    private Short buildingCentury;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "architectural_style_id")
    private ArchitecturalStyle architecturalStyle;

    @JsonIgnoreProperties("constructions")
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "construction_architect",
            joinColumns = @JoinColumn(name = "construction_id"),
            inverseJoinColumns = @JoinColumn(name = "architect_id")
    )
    private List<Architect> architects = new ArrayList<>();

    private String description;

    @JsonIgnoreProperties("construction")
    @OneToMany(mappedBy = "construction")
    private List<ConstructionImage> images = new ArrayList<>();
}
