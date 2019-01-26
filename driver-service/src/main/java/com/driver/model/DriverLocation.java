package com.driver.model;

import lombok.Data;

@Data
public class DriverLocation {
    private int id;
    private double latitude, longitude, distance;
}
