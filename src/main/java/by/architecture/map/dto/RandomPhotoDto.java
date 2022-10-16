package by.architecture.map.dto;

import by.architecture.map.entity.Source;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter(AccessLevel.PUBLIC)
public class RandomPhotoDto {

    private Integer id;
    private String urlAddressToPhoto;
    private Integer constructionId;
    private String constructionName;
    private AddressDto address;
    private Source source;
}
