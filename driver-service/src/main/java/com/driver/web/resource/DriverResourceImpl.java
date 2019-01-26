package com.driver.web.resource;


import com.driver.service.DriverService;
import com.driver.web.model.FindDriverRequest;
import com.driver.web.model.FindDriverResponse;
import com.driver.web.model.LocationRequest;
import com.driver.web.model.LocationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;


@RestController
public class DriverResourceImpl implements DriverResource{
    private static final Logger logger = LoggerFactory.getLogger(DriverResourceImpl.class);

    @Resource
    private DriverService driverService;

    public ResponseEntity updateLocation(@PathVariable int id, @RequestBody @Valid LocationRequest locationRequest) {
        logger.info("Update location: id={}, locationRequest={}", id, locationRequest);
        LocationResponse locationResponse = driverService.updateLocation(id, locationRequest);
        logger.info("Update location response: {}", locationResponse);
        return ResponseEntity.status(locationResponse.getHttpStatus()).body(locationResponse);
    }

    public ResponseEntity findDriver(@RequestBody @Valid FindDriverRequest findDriverRequest) {
        logger.info("Find driver for findDriverRequest={}", findDriverRequest);
        FindDriverResponse findDriverResponse = driverService.findDriver(findDriverRequest);
        logger.info("Find driver response: {}", findDriverResponse);
        return ResponseEntity.status(findDriverResponse.getHttpStatus()).body(findDriverResponse);
    }
}
