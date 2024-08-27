package com.architecture_map.belarus.entity;

import com.architecture_map.belarus.entity.image.Image;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
@ToString
public class ArchitecturalStyle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;

    @ManyToMany
    @JoinTable(
            name = "architectural_style_architectural_attribute",
            joinColumns = {@JoinColumn(name = "architectural_style_id")},
            inverseJoinColumns = {@JoinColumn(name = "architectural_attribute_id")}
    )
    private Set<ArchitecturalAttribute> attributes = new HashSet<>();

    @JsonIgnoreProperties({"construction"})
    @OneToOne
    @JoinColumn(name = "demonstrative_image_id", referencedColumnName = "id")
    private Image demonstrativeImage;

    private String shortDescription;
    private String yearsActive;
}
