package com.architecture_map.belarus.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter(AccessLevel.PUBLIC)
public class ConstructionDto {

    private String name;
    private AddressDto address;
    private Integer architecturalStyleId;
    private String buildingTime;
    private String description;
}