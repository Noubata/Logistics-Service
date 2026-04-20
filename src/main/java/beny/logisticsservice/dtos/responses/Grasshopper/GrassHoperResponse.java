package beny.logisticsservice.dtos.responses.Grasshopper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GrassHoperResponse {
        private Point point;

        private List<Double> extent;

        private String name;

        private String country;

        private String countrycode;

        private String state;

        private String district;

        private String street;

        private String postcode;
        private Integer osmId;
        private String osmType;

        private String osmKey;

        private String osmValue;
    }

