package beny.logisticsservice.producer;

import beny.logisticsservice.event.ShipmentCreated;
import beny.logisticsservice.event.ShipmentDelivered;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ShipmentEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishShipmentCreated(ShipmentCreated event) {
        try {
            kafkaTemplate.send("SHIPMENT_CREATED", event.getOrderId().toString(), event);
            log.info("Published SHIPMENT_CREATED event for order: {}", event.getOrderId());
        } catch (Exception e) {
            log.error("Failed to publish SHIPMENT_CREATED event for order: {}", event.getOrderId(), e);
        }
    }

    public void publishShipmentDelivered(ShipmentDelivered event) {
        try {
            kafkaTemplate.send("SHIPMENT_DELIVERED", event.getOrderId().toString(), event);
            log.info("Published SHIPMENT_DELIVERED event for order: {}", event.getOrderId());
        } catch (Exception e) {
            log.error("Failed to publish SHIPMENT_DELIVERED event for order: {}", event.getOrderId(), e);
        }
    }
}
