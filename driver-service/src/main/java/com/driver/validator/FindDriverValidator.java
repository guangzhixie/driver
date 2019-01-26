package com.driver.validator;

import com.driver.web.model.FindDriverRequest;
import com.driver.web.model.FindDriverResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.driver.validator.util.*;

import java.util.ArrayList;
import java.util.List;

import static com.driver.enums.LocationUpdateError.INVALID_LATITUDE;
import static com.driver.enums.LocationUpdateError.INVALID_LONGITUDE;

@Service
public class FindDriverValidator {
    private static final Logger logger = LoggerFactory.getLogger(FindDriverValidator.class);

    public FindDriverResponse validate(FindDriverRequest findDriverRequest) {
        List<String> errors = new ArrayList<>();

        // validate latitude
        Double latitude = findDriverRequest.getLatitude();
        if (!ValidatorUtil.isValidLatitude(latitude)) {
            logger.warn("Latitude {} is invalid.", latitude);
            errors.add(INVALID_LATITUDE.getMessage());
        }

        // validate longitude
        Double longitude = findDriverRequest.getLongitude();
        if (!ValidatorUtil.isValidLongitude(longitude)) {
            logger.warn("Longitude {} is invalid.", longitude);
            errors.add(INVALID_LONGITUDE.getMessage());
        }

        return errors.size() > 0 ? new FindDriverResponse(HttpStatus.UNPROCESSABLE_ENTITY, errors, null)
                : null;
    }
}
