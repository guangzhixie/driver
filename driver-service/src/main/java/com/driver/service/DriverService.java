package com.driver.service;

import com.driver.web.model.BasicResponse;
import com.driver.web.model.FindDriverRequest;
import com.driver.web.model.FindDriverResponse;
import com.driver.web.model.LocationRequest;

public interface DriverService {
    BasicResponse updateLocation(int id, LocationRequest locationUpdateRequest);

    FindDriverResponse findDriver(FindDriverRequest findDriverRequest);
}
