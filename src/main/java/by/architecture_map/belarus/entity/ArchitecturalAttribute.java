package by.architecture_map.belarus.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Attributes that may be characteristic of the architectural
 * style, for example, pilasters, lancet windows, etc.
 */

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class ArchitecturalAttribute extends BaseEntity {

    private String name;
    private String description;

    @OneToOne
    @JoinColumn(name = "demonstrative_image_id", referencedColumnName = "id")
    private Image demonstrativeImage;
}
