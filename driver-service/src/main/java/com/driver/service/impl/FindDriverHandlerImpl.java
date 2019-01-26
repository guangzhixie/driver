package com.driver.service.impl;

import com.driver.cache.DriverLocationCache;
import com.driver.model.DriverLocation;
import com.driver.model.LatLang;
import com.driver.service.DistanceCalculator;
import com.driver.service.FindDriverHandler;
import com.driver.web.model.FindDriverRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

@Service
public class FindDriverHandlerImpl implements FindDriverHandler {
    //TODO: move to centralized config
    private static final int DEFAULT_RADIUS = 500;
    private static final int DEFAULT_LIMIT = 10;

    @Resource
    private DriverLocationCache driverLocationCache;

    @Resource
    private DistanceCalculator distanceCalculator;

    //TODO: use GeoHash
    @Override
    public List<DriverLocation> handle(FindDriverRequest findDriverRequest) {
        LatLang userLocation = new LatLang(findDriverRequest.getLatitude(), findDriverRequest.getLongitude());
        int radius = findDriverRequest.getRadius() == null ? DEFAULT_RADIUS : findDriverRequest.getRadius();
        int limit = findDriverRequest.getLimit() == null ? DEFAULT_LIMIT : findDriverRequest.getLimit();

        List<DriverLocation> driverLocations = new ArrayList<>();
        ConcurrentMap<Integer, LatLang> currentDriverLocations = driverLocationCache.getAllCurrentDriverLocations();
        for (Map.Entry<Integer, LatLang> entry : currentDriverLocations.entrySet()) {
            LatLang driverLocation = entry.getValue();
            double distance = distanceCalculator.calculateDistanceInMeters(userLocation, driverLocation);
            if (distance <= radius) {
                driverLocations.add(new DriverLocation(entry.getKey(), driverLocation.getLatitude(), driverLocation.getLongitude(), distance));
            }
            if (driverLocations.size() >= limit) {
                break;
            }
        }

        return driverLocations;
    }
}
