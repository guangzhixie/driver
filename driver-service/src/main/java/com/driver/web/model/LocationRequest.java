package com.driver.web.model;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class LocationRequest {
    private Double latitude, longitude;
    private Float accuracy;
}
