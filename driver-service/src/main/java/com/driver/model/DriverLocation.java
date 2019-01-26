package com.driver.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DriverLocation {
    private int id;
    private double latitude, longitude, distance;
}
