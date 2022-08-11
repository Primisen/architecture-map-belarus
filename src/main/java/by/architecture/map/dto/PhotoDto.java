package by.architecture.map.dto;

import by.architecture.map.entity.Construction;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter(AccessLevel.PUBLIC)
public class PhotoDto {

    private Construction construction;
    private String urlAddressToPhoto;
}
