package com.driver.web.resource;


import com.driver.service.DriverService;
import com.driver.web.model.LocationRequest;
import com.driver.web.model.LocationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;


@RestController
public class DriverResource {
    private static final Logger logger = LoggerFactory.getLogger(DriverResource.class);

    @Resource
    private DriverService driverService;

    @RequestMapping(value = "/drivers/{id}/location", method = PUT, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity updateLocation(@PathVariable int id, @RequestBody @Valid LocationRequest locationRequest) {
        logger.info("Update location: id={}, locationRequest={}", id, locationRequest);
        LocationResponse locationResponse = driverService.updateLocation(id, locationRequest);
        logger.info("Update location response: {}", locationResponse);
        return ResponseEntity.status(locationResponse.getHttpStatus()).body(locationResponse);
    }
}
