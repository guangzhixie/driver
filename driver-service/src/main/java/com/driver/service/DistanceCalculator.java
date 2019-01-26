package com.driver.service;

import com.driver.model.LatLang;

public interface DistanceCalculator {
    double calculateDistanceInMeters(LatLang point1, LatLang point2);
}
