package com.driver.web.model;

import com.driver.model.DriverLocation;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class FindDriverResponse extends BasicResponse {

    private List<DriverLocation> driverLocations;

    @Builder
    public FindDriverResponse(HttpStatus httpStatus, List<String> errors, List<DriverLocation> driverLocations) {
        super(httpStatus, errors);
        this.driverLocations = driverLocations;
    }

    public Object constructResponseBody() {
        return errors != null? this : driverLocations;
    }
}
