package com.architecture_map.belarus.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter(AccessLevel.PUBLIC)
public class AddressDto {

    private String locality;
    private String district;
    private String region;

    private String street;
    private String houseNumber;
}
