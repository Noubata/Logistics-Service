package beny.logisticsservice.services;

import beny.logisticsservice.event.DeliveryUpdateMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Component
@EnableScheduling
@RequiredArgsConstructor
@ConditionalOnProperty(name = "logistics.simulation.enabled", havingValue = "true")
public class DriverSimulationScheduler {

    private final LocationBroadcastService broadcastService;

    private static final String ORDER_ID  = "demo-order-001";
    private static final double DEST_LAT  = 6.6018;
    private static final double DEST_LNG  = 3.3515;

    private double currentLat = 6.4550;
    private double currentLng = 3.3841;
    private int    etaMinutes = 30;

    @Scheduled(fixedDelay = 5000)
    public void tick() {
        double t = ThreadLocalRandom.current().nextDouble(0.02, 0.05);
        currentLat = lerp(currentLat, DEST_LAT, t);
        currentLng = lerp(currentLng, DEST_LNG, t);
        etaMinutes = Math.max(1, etaMinutes - 1);

        DeliveryUpdateMessage message = DeliveryUpdateMessage.builder()
                .shipmentId(ORDER_ID)
                .lat(currentLat)
                .lng(currentLng)
                .estimatedArrivalMins(etaMinutes)
                .status("IN_TRANSIT")
                .build();

        broadcastService.broadcastLocationUpdate(ORDER_ID, message);
        log.debug("Sim tick → lat={} lng={} eta={}min", currentLat, currentLng, etaMinutes);
    }

    private double lerp(double from, double to, double t) {
        return from + (to - from) * t;
    }
}