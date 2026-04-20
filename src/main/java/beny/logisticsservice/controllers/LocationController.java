package beny.logisticsservice.controllers;

import beny.logisticsservice.event.DeliveryUpdateMessage;
import beny.logisticsservice.dtos.requests.LocationUpdateRequest;
import beny.logisticsservice.services.LocationBroadcastService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/logistics")
@RequiredArgsConstructor
public class LocationController {

    private final LocationBroadcastService broadcastService;

    @PostMapping("/shipments/{id}/location")
    public ResponseEntity<DeliveryUpdateMessage> updateLocation(
            @PathVariable UUID id,
            @Valid @RequestBody LocationUpdateRequest request) {

        log.info("Location update: shipmentId={} lat={} lng={}", id, request.getLat(), request.getLng());

        DeliveryUpdateMessage message = DeliveryUpdateMessage.builder()
                .shipmentId(id.toString())
                .lat(request.getLat())
                .lng(request.getLng())
                // TODO: wire in etaMinutes + status from ShipmentService once available
                .build();

        broadcastService.broadcastLocationUpdate(id.toString(), message);

        return ResponseEntity.ok(message);
    }
}