package com.architecture_map.belarus.entity;

import com.architecture_map.belarus.entity.image.ArchitecturalAttributeImage;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter(AccessLevel.PUBLIC)
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class ArchitecturalAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;

    @OneToMany(mappedBy = "architecturalAttribute", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<ArchitecturalAttributeImage> images = new HashSet<>();
}
