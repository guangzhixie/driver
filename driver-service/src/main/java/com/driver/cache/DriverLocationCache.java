package com.driver.cache;

import com.driver.model.LatLang;
import com.driver.persistence.entity.DriverLocationEntity;
import com.driver.persistence.repository.DriverRepository;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

@Component
public class DriverLocationCache {
    private static final Logger logger = LoggerFactory.getLogger(DriverLocationCache.class);

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
        List<DriverLocationEntity> lastLocations = driverRepository.findAll();
        logger.info("Preload {} driver last locations from DB", lastLocations.size());
        lastLocations.forEach(
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
