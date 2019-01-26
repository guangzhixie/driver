package com.driver.service;

import com.driver.web.model.LocationRequest;

public interface UpdateLocationHandler {
    void handle(Integer id, LocationRequest locationRequest);
}
