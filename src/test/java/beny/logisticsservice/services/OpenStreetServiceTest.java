package beny.logisticsservice.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OpenStreetServiceTest {
    @Autowired
    private OpenStreetService openStreetService;
    @Test
    @DisplayName("""
            Test That when i call MapService GetAddressGecoding with a valid address
            I can Get My Current Longitude And Latitude
            """)
    void testGetGecodingAddress() {
        String address = "Lagos";
        Map<String,String> geoCodeLocations = openStreetService.getAddressGecoding(address);
        assertNotNull(geoCodeLocations);
        assertNotNull(geoCodeLocations.get("lat"));
        assertNotNull(geoCodeLocations.get("lng"));
    }

    @Test
    @DisplayName("""
            Test That when i call MapService GetGetAddress With a new Address It returns right logitude and lattitus
            """)
    void testGetAddressGecodingWithNewAddress() {
        String addres = "abuja";
        Map<String,String> geoCodeLocations = openStreetService.getAddressGecoding(addres);
        assertNotNull(geoCodeLocations);
        assertNotNull(geoCodeLocations.get("lat"));
        assertNotNull(geoCodeLocations.get("lng"));
    }
}