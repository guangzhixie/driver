package com.driver.service;

import com.driver.web.model.FindDriverRequest;
import com.driver.web.model.FindDriverResponse;
import com.driver.web.model.LocationRequest;
import com.driver.web.model.LocationResponse;

public interface DriverService {
    LocationResponse updateLocation(int id, LocationRequest locationUpdateRequest);

    FindDriverResponse findDriver(FindDriverRequest findDriverRequest);
}
