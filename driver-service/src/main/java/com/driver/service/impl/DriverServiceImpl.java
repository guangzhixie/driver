package com.driver.service.impl;

import com.driver.model.DriverLocation;
import com.driver.service.DriverService;
import com.driver.service.FindDriverHandler;
import com.driver.service.UpdateLocationHandler;
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
import java.util.Collections;
import java.util.List;

import static com.driver.enums.LocationUpdateError.SYSTEM_ERROR;

@Service
public class DriverServiceImpl implements DriverService {
    private static final Logger logger = LoggerFactory.getLogger(DriverServiceImpl.class);

    @Resource
    private LocationUpdateValidator locationUpdateValidator;

    @Resource
    private FindDriverValidator findDriverValidator;

    @Resource
    private UpdateLocationHandler updateLocationHandler;

    @Resource
    private FindDriverHandler findDriverHandler;

    @Override
    public BasicResponse updateLocation(int id, LocationRequest locationRequest) {
        BasicResponse validationResponse = locationUpdateValidator.validate(id, locationRequest);
        if (validationResponse != null) {
            return validationResponse;
        }

        try {
            updateLocationHandler.handle(id, locationRequest);
            return new BasicResponse(HttpStatus.OK, null);
        } catch (Exception e) {
            logger.warn("Failed to update location for request={}", locationRequest, e);
            return new BasicResponse(HttpStatus.UNPROCESSABLE_ENTITY, Collections.singletonList(SYSTEM_ERROR.getMessage()));
        }
    }

    @Override
    public FindDriverResponse findDriver(FindDriverRequest findDriverRequest) {
        FindDriverResponse validationResponse = findDriverValidator.validate(findDriverRequest);
        if (validationResponse != null) {
            return validationResponse;
        }

        try {
            List<DriverLocation> driverLocations = findDriverHandler.handle(findDriverRequest);
            return new FindDriverResponse(HttpStatus.OK, null, driverLocations);
        } catch (Exception e) {
            logger.warn("Failed to find driver for request={}", findDriverRequest, e);
            return new FindDriverResponse(HttpStatus.UNPROCESSABLE_ENTITY, Collections.singletonList(SYSTEM_ERROR.getMessage()), null);
        }
    }
}
