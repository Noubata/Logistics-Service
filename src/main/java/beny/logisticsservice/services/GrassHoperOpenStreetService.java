package beny.logisticsservice.services;

import beny.logisticsservice.dtos.responses.Grasshopper.GrassHoperResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;
@Slf4j
@Component
@Getter
@Setter

public class GrassHoperOpenStreetService implements MapService {
    @Value("${nominatim.url}")
    private String nominatimBaseUrl;
    @Value("${grashoper.api.key}")
    private String api_key;
    private final RestClient restClient = RestClient.create();
    @Override
    public Map<String, String> getAddressGecoding(String address) {
        log.info(api_key);
        String url = "https://graphhopper.com/api/1/geocode?q={address}&locale=de&key={api_key}";
        try {
            List<GrassHoperResponse> results = restClient.get()
                    .uri(url, address,api_key)
                    .retrieve()
                    .body(new ParameterizedTypeReference<List<GrassHoperResponse>>() {});

            if (results == null || results.isEmpty()) {
                throw new RuntimeException("No geocoding result found for address: " + address);
            }
            return Map.of(
                    "lat", results.get(0).getPoint().getLat(),
                    "lng", results.get(0).getPoint().getLat()
            );

        } catch (Exception e) {
            log.error("Geocoding failed for address: {}", address, e);
            throw e;
        }

    }
}
