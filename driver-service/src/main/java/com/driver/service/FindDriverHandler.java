package com.driver.service;

import com.driver.model.DriverLocation;
import com.driver.web.model.FindDriverRequest;

import java.util.List;

public interface FindDriverHandler {
    List<DriverLocation> handle(FindDriverRequest findDriverRequest);
 }
