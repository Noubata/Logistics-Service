package beny.logisticsservice.event;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeliveryUpdateMessage {
    private String  shipmentId;
    private Double  lat;
    private Double  lng;
    private Integer estimatedArrivalMins;
    private String  status;
}