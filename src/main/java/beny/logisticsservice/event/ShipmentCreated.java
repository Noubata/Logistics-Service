package beny.logisticsservice.event;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ShipmentCreated {
    private UUID shipmentId;
    private UUID orderId;
    private UUID driverId;
    private String status;
    private String originAddress;
    private String destinationAddress;
    private BigDecimal originLat;
    private BigDecimal originLng;
    private BigDecimal destLat;
    private BigDecimal destLng;
    private Integer etaMinutes;
    private String createdAt;
}
