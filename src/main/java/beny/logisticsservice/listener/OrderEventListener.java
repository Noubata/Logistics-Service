package beny.logisticsservice.listener;

import beny.logisticsservice.event.OrderConfirmed;
import beny.logisticsservice.services.LogisticsServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderEventListener {

    private final LogisticsServices logisticsServices;

    @KafkaListener(topics = "ORDER_CONFIRMED", groupId = "logistics-service")
    public void handleOrderConfirmed(OrderConfirmed event) {
        log.info("Received ORDER_CONFIRMED event for order: {}", event.getOrderId());
        try {
            logisticsServices.createShipmentFromOrder(event);
            log.info("Successfully created shipment for order: {}", event.getOrderId());
        } catch (Exception e) {
            log.error("Failed to create shipment for order: {}", event.getOrderId(), e);
        }
    }
}
