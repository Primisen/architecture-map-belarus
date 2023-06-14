package by.architecture.map.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "construction")
@RequiredArgsConstructor
@Getter
@Setter(AccessLevel.PUBLIC)
public class Construction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @OneToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @OneToMany(mappedBy = "construction", cascade=CascadeType.PERSIST)
    private Set<Photo> photos;

    @ManyToOne
    @JoinColumn(name = "architectural_style_id")
    private ArchitecturalStyle architecturalStyle;

    @Column(name = "building_time", length = 20)
    private String buildingTime;

    //String architect
}
