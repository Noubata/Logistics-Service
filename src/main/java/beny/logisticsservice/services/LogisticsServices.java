package beny.logisticsservice.services;

import beny.logisticsservice.producer.ShipmentEventProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LogisticsServices {

    private final ShipmentEventProducer shipmentEventProducer;
}
