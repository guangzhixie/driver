package com.driver.cache;

import com.driver.model.LatLang;
import com.driver.persistence.repository.DriverRepository;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
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

    @Resource
    private DriverRepository driverRepository;

    @PostConstruct
    public void preLoad() {
        // preload each driver's last location from DB
        driverRepository.findAll().forEach(
                driverLocationEntity ->
                        locationCache.put(driverLocationEntity.getId(),
                                new LatLang(driverLocationEntity.getLatitude(), driverLocationEntity.getLongitude()))
        );
    }

    public void updateLocation(int id, LatLang latestLocation) {
        locationCache.put(id, latestLocation);
    }

    public ConcurrentMap<Integer, LatLang> getAllCurrentDriverLocations() {
        return locationCache.asMap();
    }
}
