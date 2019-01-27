package com.driver.service.impl;

import com.driver.cache.DriverLocationCache;
import com.driver.model.LatLang;
import com.driver.persistence.entity.DriverLocationEntity;
import com.driver.persistence.repository.DriverRepository;
import com.driver.service.UpdateLocationHandler;
import com.driver.web.model.LocationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class UpdateLocationHandlerImpl implements UpdateLocationHandler {
    private static final Logger logger = LoggerFactory.getLogger(UpdateLocationHandlerImpl.class);

    //TODO: move to centralized config
    private static final int CONCURRENCY_LEVEL = 5;

    private static ExecutorService THREAD_POOL = Executors.newFixedThreadPool(CONCURRENCY_LEVEL);

    @Resource
    private DriverRepository driverRepository;

    @Resource
    private DriverLocationCache driverLocationCache;

    @Override
    @Transactional
    public void handle(Integer id, LocationRequest locationRequest) {
        driverLocationCache.updateLocation(id, new LatLang(locationRequest.getLatitude(), locationRequest.getLongitude()));
        //TODO: handle accuracy

        // update location in DB in async
        THREAD_POOL.execute(() -> updateLocationInDB(id, locationRequest));
    }

    private void updateLocationInDB(Integer id, LocationRequest locationRequest) {
        try {
            LocalDateTime currentTimeInGmt = LocalDateTime.now(Clock.systemUTC());
            DriverLocationEntity entityToUpdate = DriverLocationEntity.builder()
                    .id(id)
                    .latitude(locationRequest.getLatitude())
                    .longitude(locationRequest.getLongitude())
                    .accuracy(locationRequest.getAccuracy())
                    .gmt_modified(currentTimeInGmt)
                    .build();
            if (!driverRepository.existsById(id)) {
                entityToUpdate.setGmt_create(currentTimeInGmt);
            }
            driverRepository.save(entityToUpdate);
        } catch (Exception e) {
            logger.error("Failed to update DB. id={}, locationRequest={}", id, locationRequest, e);
        }
    }
}
