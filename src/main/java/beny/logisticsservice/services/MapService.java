package beny.logisticsservice.services;

import beny.logisticsservice.dtos.responses.OpenStreet.GeoCodingResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.net.http.HttpClient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@Slf4j
public class MapService {
    @Value("${nominatim.url}")
    private String nominatimBaseUrl;
    @Value("${nominatim.user.agent}")
    private String userAgent;
    private final RestClient restClient = RestClient.create();
    public MapService() {
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
