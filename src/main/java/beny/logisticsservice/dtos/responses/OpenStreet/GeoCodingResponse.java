package beny.logisticsservice.dtos.responses.OpenStreet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GeoCodingResponse {
    private Integer placeId;
    private String licence;
    private String osmType;
    private Integer osmId;
    private String lat;
    private String lon;
    private String category;
    private String type;
    private Integer placeRank;
    private Double importance;
    private String addresstype;
    private String name;
    private String displayName;
    private List<String> boundingbox;
}
