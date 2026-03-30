package by.architecture_map.belarus.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class ArchitecturalStyle extends BaseEntity {

    private String name;
    private String description;

    @ManyToMany
    @JoinTable(
            name = "architectural_style_architectural_attribute",
            joinColumns = @JoinColumn(name = "architectural_style_id"),
            inverseJoinColumns = @JoinColumn(name = "architectural_attribute_id")
    )
    private List<ArchitecturalAttribute> attributes = new ArrayList<>();

    @JsonIgnoreProperties("construction")
    @OneToOne
    @JoinColumn(name = "demonstrative_image_id", referencedColumnName = "id")
    private Image demonstrativeImage;

    private String shortDescription;
    private String yearsActive;
}
