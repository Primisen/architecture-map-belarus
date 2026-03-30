package by.architecture_map.belarus.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Architect extends BaseEntity {

    private String name;
    private String middleName;
    private String surname;

    /**
     * There are cases when there is no the exact date the life of architect,
     * but only an approximate time, for example: "end of the 17th century - 1756", so field has a string type
     */
    private String yearsOfLife;
    private String shortWorkDescription;
    private String biographicalArticle;

    @OneToOne
    @JoinColumn(name = "portrait_image_id", referencedColumnName = "id")
    private Image portraitImage;

    @JsonIgnoreProperties("architects")
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "construction_architect",
            joinColumns = @JoinColumn(name = "architect_id"),
            inverseJoinColumns = @JoinColumn(name = "construction_id")
    )
    private List<Construction> construction = new ArrayList<>();
}