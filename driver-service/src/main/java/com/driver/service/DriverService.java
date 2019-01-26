package com.driver.service;

import com.driver.web.model.LocationRequest;
import com.driver.web.model.LocationResponse;

public interface DriverService {
    public LocationResponse updateLocation(Long id, LocationRequest locationUpdateRequest);
}
