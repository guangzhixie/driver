package com.driver.cache;

import com.driver.model.LatLang;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentMap;

@Component
public class DriverLocationCache {

    //TODO: move to centralized config
    private static final int CONCURRENCY_LEVEL = 10;
    private static final int MAX_SIZE = 100000;

    private static Cache<Integer, LatLang> locationCache = CacheBuilder.newBuilder()
            .concurrencyLevel(CONCURRENCY_LEVEL)
            .maximumSize(MAX_SIZE)
            .build();

    @PostConstruct
    public void preLoad() {
        //TODO: preload each driver's last location from DB
    }

    public void updateLocation(int id, LatLang latestLocation) {
        locationCache.put(id, latestLocation);
    }

    public ConcurrentMap<Integer, LatLang> getAllCurrentDriverLocations() {
        return locationCache.asMap();
    }
}
