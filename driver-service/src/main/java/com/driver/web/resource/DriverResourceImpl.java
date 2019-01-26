package com.driver.web.resource;


import com.driver.service.DriverService;
import com.driver.web.model.BasicResponse;
import com.driver.web.model.FindDriverRequest;
import com.driver.web.model.FindDriverResponse;
import com.driver.web.model.LocationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class DriverResourceImpl implements DriverResource{
    private static final Logger logger = LoggerFactory.getLogger(DriverResourceImpl.class);

    @Resource
    private DriverService driverService;

    @Override
    public ResponseEntity updateLocation(@PathVariable Integer id, @RequestBody LocationRequest locationRequest) {
        logger.info("Update location: id={}, locationRequest={}", id, locationRequest);
        BasicResponse response = driverService.updateLocation(id, locationRequest);
        logger.info("Update location response: {}", response);
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }

    @Override
    public ResponseEntity findDriver(@RequestParam("latitude") Double latitude, @RequestParam("longitude") Double longitude,
                                     @RequestParam(value="radius", required = false) Integer radius,
                                     @RequestParam(value="limit", required = false) Integer limit) {
        FindDriverRequest findDriverRequest = new FindDriverRequest(latitude, longitude, radius, limit);
        logger.info("Find driver for findDriverRequest={}", findDriverRequest);
        FindDriverResponse findDriverResponse = driverService.findDriver(findDriverRequest);
        logger.info("Find driver response: {}", findDriverResponse);
        return ResponseEntity.status(findDriverResponse.getHttpStatus()).body(findDriverResponse.constructResponseBody());
    }
}
