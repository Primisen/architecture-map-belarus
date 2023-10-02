package com.architecture_map.belarus.entity;

import com.architecture_map.belarus.entity.image.ConstructionImage;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "construction")
@RequiredArgsConstructor
@Getter
@Setter(AccessLevel.PUBLIC)
@ToString
public class Construction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String buildingTime;

    @OneToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @ManyToOne
    @JoinColumn(name = "architectural_style_id")
    private ArchitecturalStyle architecturalStyle;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "construction_architect",
            joinColumns = { @JoinColumn(name = "construction_id")},
            inverseJoinColumns = {@JoinColumn(name = "architect_id")}
    )
    private Set<Architect> architects = new HashSet<>();

    private String description;

    @JsonIgnoreProperties("construction")
    @OneToMany(mappedBy = "construction")
    private Set<ConstructionImage> images = new HashSet<>();
}
