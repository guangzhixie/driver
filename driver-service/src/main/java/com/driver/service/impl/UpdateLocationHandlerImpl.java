package com.driver.service.impl;

import com.driver.cache.DriverLocationCache;
import com.driver.model.LatLang;
import com.driver.service.UpdateLocationHandler;
import com.driver.web.model.LocationRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class UpdateLocationHandlerImpl implements UpdateLocationHandler {
    //TODO: move to centralized config
    private static final int CONCURRENCY_LEVEL = 5;

    private static ExecutorService THREAD_POOL = Executors.newFixedThreadPool(CONCURRENCY_LEVEL);

    @Resource
    private DriverLocationCache driverLocationCache;

    @Override
    public void handle(Integer id, LocationRequest locationRequest) {
        driverLocationCache.updateLocation(id, new LatLang(locationRequest.getLatitude(), locationRequest.getLongitude()));
        //TODO: handle accuracy

        // update location in DB in async
        THREAD_POOL.execute(()->updateLocationInDB(locationRequest));
    }

    private void updateLocationInDB(LocationRequest locationRequest) {
        //TODO: update location in DB
    }
}
