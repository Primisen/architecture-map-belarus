package com.architecture_map.belarus.dto;

import com.architecture_map.belarus.entity.ArchitecturalAttribute;
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

}
