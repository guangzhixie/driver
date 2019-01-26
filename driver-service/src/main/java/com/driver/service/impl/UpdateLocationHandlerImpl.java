package com.driver.service.impl;

import com.driver.cache.DriverLocationCache;
import com.driver.model.LatLang;
import com.driver.service.UpdateLocationHandler;
import com.driver.web.model.LocationRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UpdateLocationHandlerImpl implements UpdateLocationHandler {

    @Resource
    private DriverLocationCache driverLocationCache;

    @Override
    public void handle(Integer id, LocationRequest locationRequest) {
        driverLocationCache.updateLocation(id, new LatLang(locationRequest.getLatitude(), locationRequest.getLongitude()));
        //TODO: handle accuracy
        //TODO: update location in DB
    }
}
