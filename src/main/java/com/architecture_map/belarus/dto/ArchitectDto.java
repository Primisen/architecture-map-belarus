package com.architecture_map.belarus.dto;

import com.architecture_map.belarus.entity.image.ArchitectImage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class ArchitectDto {

    private String name;
    private String surname;
    private String middleName;
    private String yearsOfLife;
    private String shortWorkDescription;
    private String biographicalArticle;
    private ArchitectImage architectImage;
}
