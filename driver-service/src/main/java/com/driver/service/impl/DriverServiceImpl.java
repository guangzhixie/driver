package com.driver.service.impl;

import com.driver.cache.DriverLocationCache;
import com.driver.model.LatLang;
import com.driver.service.DriverService;
import com.driver.validator.FindDriverValidator;
import com.driver.validator.LocationUpdateValidator;
import com.driver.web.model.BasicResponse;
import com.driver.web.model.FindDriverRequest;
import com.driver.web.model.FindDriverResponse;
import com.driver.web.model.LocationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class DriverServiceImpl implements DriverService {
    private static final Logger logger = LoggerFactory.getLogger(DriverServiceImpl.class);

    @Resource
    private LocationUpdateValidator locationUpdateValidator;

    @Resource
    private FindDriverValidator findDriverValidator;

    @Resource
    private DriverLocationCache driverLocationCache;

    @Override
    public BasicResponse updateLocation(int id, LocationRequest locationRequest) {
        BasicResponse validationResponse = locationUpdateValidator.validate(id, locationRequest);
        if (validationResponse != null) {
            return validationResponse;
        }

        driverLocationCache.updateLocation(id, new LatLang(locationRequest.getLatitude(), locationRequest.getLongitude()));
        //TODO: update to DB

        return new BasicResponse(HttpStatus.OK, null);
    }

    @Override
    public FindDriverResponse findDriver(FindDriverRequest findDriverRequest) {
        FindDriverResponse validationResponse = findDriverValidator.validate(findDriverRequest);
        if (validationResponse != null) {
            return validationResponse;
        }
        //TODO: find driver
        return new FindDriverResponse(HttpStatus.OK, null, new ArrayList<>(0));
    }
}
