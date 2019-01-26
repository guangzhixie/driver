package com.driver.service.impl;

import com.driver.service.DriverService;
import com.driver.validator.LocationUpdateValidator;
import com.driver.web.model.LocationRequest;
import com.driver.web.model.LocationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DriverServiceImpl implements DriverService {
    private static final Logger logger = LoggerFactory.getLogger(DriverServiceImpl.class);

    @Resource
    private LocationUpdateValidator locationUpdateValidator;

    @Override
    public LocationResponse updateLocation(int id, LocationRequest locationRequest) {
        LocationResponse locationResponseAfterValidation = locationUpdateValidator.validate(id, locationRequest);
        if (locationResponseAfterValidation != null) {
            return locationResponseAfterValidation;
        }

        //TODO: update location after validation
        return new LocationResponse(HttpStatus.OK, null);
    }
}
