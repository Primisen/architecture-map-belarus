package by.architecture_map.belarus.dto;

import lombok.Data;

@Data
public class ConstructionDTO {
    private String architecturalStyleId;
    private String region;
    private String district;
    private String buildingCenturyFrom;
    private String buildingCenturyTo;
}
