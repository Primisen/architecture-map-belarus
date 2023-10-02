package com.architecture_map.belarus.entity.image;

import com.architecture_map.belarus.entity.Construction;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "construction_image")
@RequiredArgsConstructor
@Getter
@Setter(AccessLevel.PUBLIC)
@ToString
@PrimaryKeyJoinColumn(name = "image_id")
public class ConstructionImage extends Image{

    @JsonIgnoreProperties("images")
    @ManyToOne
    @JoinColumn(name = "construction_id")
    private Construction construction;
    private String takenTime;

}
