package beny.logisticsservice.event;

import lombok.Data;

import java.util.UUID;

@Data
public class ShipmentDelivered {
    private UUID shipmentId;
    private UUID orderId;
    private UUID driverId;
    private String status;
    private String deliveredAt;
}
