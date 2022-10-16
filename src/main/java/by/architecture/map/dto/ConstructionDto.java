package by.architecture.map.dto;

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
}
