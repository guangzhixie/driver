package com.driver.web.resource;


import com.driver.service.DriverService;
import com.driver.web.model.LocationRequest;
import com.driver.web.model.LocationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
public class DriverResource {
    private static final Logger logger = LoggerFactory.getLogger(DriverResource.class);

    @Resource
    private DriverService driverService;

    @RequestMapping(value = "/drivers/{id}/location", produces = "application/json")
    @ResponseBody
    public ResponseEntity updateLocation(@PathVariable Long id, @RequestBody LocationRequest locationRequest) {
        logger.info("Update location: id={}, locationRequest={}", id, locationRequest);
        LocationResponse locationResponse = driverService.updateLocation(id, locationRequest);
        logger.info("Update location response: {}", locationResponse);
        return ResponseEntity.status(locationResponse.getHttpStatus()).body(locationResponse);
    }
}
