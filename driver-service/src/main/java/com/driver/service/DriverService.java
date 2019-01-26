package com.driver.service;

import com.driver.web.model.LocationRequest;
import com.driver.web.model.LocationResponse;

public interface DriverService {
    LocationResponse updateLocation(int id, LocationRequest locationUpdateRequest);
}
