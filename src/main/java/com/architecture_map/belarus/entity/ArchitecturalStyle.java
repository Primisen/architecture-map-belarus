package com.architecture_map.belarus.entity;

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
import javax.persistence.Table;
import javax.persistence.ManyToMany;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "architectural_style")
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

}
