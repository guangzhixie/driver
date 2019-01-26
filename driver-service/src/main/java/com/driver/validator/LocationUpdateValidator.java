package com.driver.validator;

import com.driver.web.model.ErrorResponse;
import com.driver.web.model.LocationRequest;
import com.driver.web.model.LocationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationUpdateValidator {
    private static final Logger logger = LoggerFactory.getLogger(LocationUpdateValidator.class);

    public LocationResponse validate(int id, LocationRequest locationRequest) {
        List<String> errors = new ArrayList<>();

        // TODO: validate request

        errors.add("Latitude should be between +/- 90");
        return new LocationResponse(HttpStatus.NOT_FOUND, new ErrorResponse(errors));
    }
}
