package com.driver.validator;

import com.driver.web.model.BasicResponse;
import com.driver.web.model.LocationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import com.driver.validator.util.*;

import static com.driver.enums.LocationUpdateError.*;

@Service
public class LocationUpdateValidator {
    private static final Logger logger = LoggerFactory.getLogger(LocationUpdateValidator.class);

    //TODO: move to centralized config
    private static final int DRIVER_ID_MIN = 1;
    private static final int DRIVER_ID_MAX = 50_000;


    public BasicResponse validate(int id, LocationRequest locationRequest) {
        List<String> errors = new ArrayList<>();
        // validate id
        if (id > DRIVER_ID_MAX || id < DRIVER_ID_MIN) {
            logger.warn("Driver id {} is invalid.", id);
            errors.add(INVALID_ID.getMessage());
            return new BasicResponse(HttpStatus.NOT_FOUND, null);
        }

        // validate latitude
        Double latitude = locationRequest.getLatitude();
        if (!ValidatorUtil.isValidLatitude(latitude)) {
            logger.warn("Latitude {} is invalid.", latitude);
            errors.add(INVALID_LATITUDE.getMessage());
        }

        // validate longitude
        Double longitude = locationRequest.getLongitude();
        if (!ValidatorUtil.isValidLongitude(longitude)) {
            logger.warn("Longitude {} is invalid.", longitude);
            errors.add(INVALID_LONGITUDE.getMessage());
        }

        // validate accuracy
        if (locationRequest.getAccuracy() == null) {
            logger.warn("Accuracy cannot be null.");
            errors.add(INVALID_ACCURACY.getMessage());
        }

        return errors.size() > 0 ? new BasicResponse(HttpStatus.UNPROCESSABLE_ENTITY, errors) : null;
    }
}
