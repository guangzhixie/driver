package com.driver.service.impl;

import com.driver.cache.DriverLocationCache;
import com.driver.model.DriverLocation;
import com.driver.service.FindDriverHandler;
import com.driver.web.model.FindDriverRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class FindDriverHandlerImpl implements FindDriverHandler {

    @Resource
    private DriverLocationCache driverLocationCache;

    @Override
    public List<DriverLocation> handle(FindDriverRequest findDriverRequest) {
        //TODO: find driver
        return new ArrayList<>(0);
    }
}
