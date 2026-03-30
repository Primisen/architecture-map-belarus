package by.architecture_map.belarus.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@PrimaryKeyJoinColumn(name = "image_id")
@Data
@EqualsAndHashCode(callSuper = true)
public class ConstructionImage extends Image {

    @JsonIgnoreProperties("images")
    @ManyToOne
    @JoinColumn(name = "construction_id")
    private Construction construction;

    private String takenTime;
}
