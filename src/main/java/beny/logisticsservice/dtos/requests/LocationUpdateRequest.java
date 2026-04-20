package beny.logisticsservice.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationUpdateRequest {
    private Double lat;
    private Double lng;
}
