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

import static com.driver.enums.LocationUpdateError.INVALID_ID;
import static com.driver.enums.LocationUpdateError.INVALID_LONGITUDE;
import static com.driver.enums.LocationUpdateError.INVALID_LATITUDE;

@Service
public class LocationUpdateValidator {
    private static final Logger logger = LoggerFactory.getLogger(LocationUpdateValidator.class);

    //TODO: move to centralized config
    private static final int DRIVER_ID_MIN = 1;
    private static final int DRIVER_ID_MAX = 50_000;
    private static final int LATITUDE_RANGE = 90;
    private static final int LONGITUDE_RANGE = 180;

    public LocationResponse validate(int id, LocationRequest locationRequest) {
        List<String> errors = new ArrayList<>();
        // validate id
        if (id > DRIVER_ID_MAX || id < DRIVER_ID_MIN) {
            logger.warn("Driver id {} is invalid.", id);
            errors.add(INVALID_ID.getMessage());
            return new LocationResponse(HttpStatus.NOT_FOUND, null);
        }

        // validate latitude
        double latitude = locationRequest.getLatitude();
        if (latitude > LATITUDE_RANGE || latitude < -LATITUDE_RANGE) {
            logger.warn("Latitude {} is invalid.", latitude);
            errors.add(INVALID_LATITUDE.getMessage());
        }

        // validate longitude
        double longitude = locationRequest.getLongitude();
        if (longitude > LONGITUDE_RANGE || longitude < -LONGITUDE_RANGE) {
            logger.warn("Longitude {} is invalid.", longitude);
            errors.add(INVALID_LONGITUDE.getMessage());
        }

        return errors.size() > 0 ? new LocationResponse(HttpStatus.UNPROCESSABLE_ENTITY, new ErrorResponse(errors)) : null;
    }
}
