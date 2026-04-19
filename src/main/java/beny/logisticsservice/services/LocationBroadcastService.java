package beny.logisticsservice.services;

import beny.logisticsservice.event.DeliveryUpdateMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LocationBroadcastService {

    private final SimpMessagingTemplate messagingTemplate;

    public void broadcastLocationUpdate(String orderId, DeliveryUpdateMessage message) {
        String destination = "/topic/delivery/" + orderId;
        log.debug("Broadcasting to {}: {}", destination, message);
        messagingTemplate.convertAndSend(destination, message);
    }

    public void broadcastStatusChange(String orderId, String newStatus) {
        DeliveryUpdateMessage message = DeliveryUpdateMessage.builder()
                .shipmentId(orderId)
                .status(newStatus)
                .build();
        broadcastLocationUpdate(orderId, message);
    }
}