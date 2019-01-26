package com.driver.web.resource;

import com.driver.web.model.LocationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


public interface DriverResource {

    @PutMapping("/drivers/{id}/location")
    @ResponseBody
    ResponseEntity updateLocation(Integer id, LocationRequest locationRequest);

    @GetMapping("/drivers")
    @ResponseBody
    ResponseEntity findDriver(Double latitude, Double longitude, Integer radius, Integer limit);
}
