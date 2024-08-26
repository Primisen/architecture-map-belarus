package com.architecture_map.belarus.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PUBLIC)
@Builder
public class SourceDto {

    private String name;
    private String url;
    private String description;
}
