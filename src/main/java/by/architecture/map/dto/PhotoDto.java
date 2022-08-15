package by.architecture.map.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter(AccessLevel.PUBLIC)
public class PhotoDto {

    private Integer constructionId;
    private String urlAddressToPhoto;
}
