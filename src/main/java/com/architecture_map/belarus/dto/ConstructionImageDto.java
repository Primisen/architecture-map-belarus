package com.architecture_map.belarus.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter(AccessLevel.PUBLIC)
public class ConstructionImageDto {

    private String url;
    private Integer sourceId;
    private String takenTime;
    private Integer constructionId;
    private boolean show;
    private String author;
}
