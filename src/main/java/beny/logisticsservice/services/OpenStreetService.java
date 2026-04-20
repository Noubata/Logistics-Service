package beny.logisticsservice.services;

import beny.logisticsservice.dtos.responses.OpenStreet.GeoCodingResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class OpenStreetService implements MapService {
    @Value("${nominatim.url}")
    private String nominatimBaseUrl;
    @Value("${nominatim.user.agent}")
    private String userAgent;
    private final RestClient restClient = RestClient.create();
    public OpenStreetService() {
    }

    public Map<String, String> getAddressGecoding(String address) {
       String url = "https://nominatim.openstreetmap.org/search?q={address}&format=jsonv2&limit=1";
            try {
                List<GeoCodingResponse> results = restClient.get()
                        .uri(url, address)
                        .header(HttpHeaders.USER_AGENT, userAgent)
                        .retrieve()
                        // Map to a List of your custom object
                        .body(new ParameterizedTypeReference<List<GeoCodingResponse>>() {});

                if (results == null || results.isEmpty()) {
                    throw new RuntimeException("No geocoding result found for address: " + address);
                }
                return Map.of(
                        "lat", results.get(0).getLat(),
                        "lng", results.get(0).getLon()
                );

            } catch (Exception e) {
                log.error("Geocoding failed for address: {}", address, e);
                throw e;
            }

    }
}
