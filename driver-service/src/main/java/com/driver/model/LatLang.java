package com.driver.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LatLang {
    private double latitude, longitude;
    private float accuracy;
}
