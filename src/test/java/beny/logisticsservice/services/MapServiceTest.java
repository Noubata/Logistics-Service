package beny.logisticsservice.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MapServiceTest {
    @Autowired
    private MapService mapService;
    @Test
    @DisplayName("""
            Test That when i call MapService GetCurrentLocation
            I can Get My Current Longitude And Latitude
            """)
    void testGetCurrentLocation() {

    }

}