package beny.logisticsservice.services;

import java.util.Map;

public interface MapService {
    Map<String, String> getAddressGecoding(String address);
}
