package com.architecture_map.belarus.entity.image;

import com.architecture_map.belarus.entity.Architect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@RequiredArgsConstructor
@Getter
@Setter(AccessLevel.PUBLIC)
@ToString
@PrimaryKeyJoinColumn(name = "image_id")
public class ArchitectImage extends Image{

    @JsonIgnoreProperties("images")
    @OneToOne
    @JoinColumn(name = "architect_id", referencedColumnName = "id")
    private Architect architect;

}
