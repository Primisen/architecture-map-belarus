package com.architecture_map.belarus.entity;

//import com.architecture_map.belarus.entity.image.ArchitectImage;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter(AccessLevel.PUBLIC)
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Architect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String middleName;
    private String surname;
    private String yearsOfLife;

    @Column(name="short_work_description", columnDefinition="TEXT")
    private String shortWorkDescription;

    private String biographicalArticle;

//    @JsonIgnoreProperties("architect")
//    @OneToOne(mappedBy = "architect")
//    private ArchitectImage image;

    @JsonIgnoreProperties("architects")
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "construction_architect",
            joinColumns = { @JoinColumn(name = "architect_id")},
            inverseJoinColumns = {@JoinColumn(name = "construction_id")}
    )
    private Set<Construction> constructions = new HashSet<>();
}
