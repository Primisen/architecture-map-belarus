package com.architecture_map.belarus.dto;

import com.architecture_map.belarus.entity.ArchitecturalAttribute;
import com.architecture_map.belarus.entity.image.Image;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter(AccessLevel.PUBLIC)
@RequiredArgsConstructor
public class ArchitecturalStyleDto {

    private String name;
    private String description;
    private Set<ArchitecturalAttribute> architecturalAttributes;
    private String shortDescription;
    private String yearsActive;
    private Image demonstrativeImage;

}
