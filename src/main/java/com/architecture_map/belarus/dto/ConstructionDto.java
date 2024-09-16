package com.architecture_map.belarus.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Getter
@Setter(AccessLevel.PUBLIC)
public class ConstructionDto {

    private String name;
    private AddressDto address;
    private Integer architecturalStyleId;
    private String buildingDate;
    private String description;;
    private Set<Integer> architectsId = new HashSet<>();
}
