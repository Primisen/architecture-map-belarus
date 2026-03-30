package by.architecture_map.belarus.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Address extends BaseEntity {
    private String locality;
    private String district;
    private String region;
    private String street;
    private String houseNumber;
}
