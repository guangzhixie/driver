package com.driver.service.impl;

import com.driver.model.LatLang;
import com.driver.service.DistanceCalculator;
import org.springframework.stereotype.Service;

/*
 * Haversine method implementation
 * Works for short distance
 */

@Service
public class DistanceCalculatorImpl implements DistanceCalculator {

    private static final double MILE_TO_KM_CONVERTER = 1.609344;
    private static final int KM_TO_M_CONVERTER = 1000;

    @Override
    public double calculateDistanceInMeters(LatLang point1, LatLang point2) {
        if (point1 == null || point2 == null) {
            return Double.MAX_VALUE;
        }

        if (point1.equals(point2)) {
            return 0;
        }

        double theta = point1.getLongitude() - point2.getLongitude();
        double lat1 = point1.getLatitude();
        double lat2 = point2.getLatitude();
        double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
        dist = Math.acos(dist);
        dist = Math.toDegrees(dist);
        dist = dist * 60 * 1.1515 * MILE_TO_KM_CONVERTER * KM_TO_M_CONVERTER;
        return dist;
    }
}
