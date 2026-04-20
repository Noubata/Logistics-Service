package beny.logisticsservice.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class GrassHoperOpenStreetServiceTest {
    @Autowired
    private GrassHoperOpenStreetService grassHoperMapService;
    @Test
    @DisplayName("""
            Test That when i call MapService GetAddressGe0coding with a valid address
            I can Get My Current Longitude And Latitude
            """)
    void testGetGecodingAddress() {
        String address = "312, Herbert Macaulay Way, Sabo Yaba, Lagos, Nigeria";
        Map<String,String> geoCodeLocations = grassHoperMapService.getAddressGecoding(address);
        assertNotNull(geoCodeLocations);
        assertNotNull(geoCodeLocations.get("lat"));
        assertNotNull(geoCodeLocations.get("lng"));
    }

    @Test
    @DisplayName("""
            Test That when i call MapService GetGetAddress With a new Address It returns right longitude and latitude
            """)
    void testGetAddressGecodingWithNewAddress() {
        String addres = "20,Ayinke,Street Shomolu lagos";
        Map<String,String> geoCodeLocations = grassHoperMapService.getAddressGecoding(addres);
        assertNotNull(geoCodeLocations);
        assertNotNull(geoCodeLocations.get("lat"));
        assertNotNull(geoCodeLocations.get("lng"));
    }
}