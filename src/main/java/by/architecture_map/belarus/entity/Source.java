package by.architecture_map.belarus.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Source extends BaseEntity {

    private String name;
    private String url;
    private String description;
}
