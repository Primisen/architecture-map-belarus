package com.architecture_map.belarus.entity.image;

import com.architecture_map.belarus.entity.ArchitecturalAttribute;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "architectural_attribute_image")
@RequiredArgsConstructor
@Getter
@Setter(AccessLevel.PUBLIC)
@ToString
@PrimaryKeyJoinColumn(name = "image_id")
public class ArchitecturalAttributeImage extends Image{

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "architectural_attribute_id")
    private ArchitecturalAttribute architecturalAttribute;
}
