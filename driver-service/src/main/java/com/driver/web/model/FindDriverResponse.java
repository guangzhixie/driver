package com.driver.web.model;

import com.driver.model.DriverLocation;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class FindDriverResponse extends BaseResponse<List<DriverLocation>> {
    @Builder
    public FindDriverResponse(HttpStatus httpStatus, List<DriverLocation> driverLocations) {
        super(httpStatus, driverLocations);
    }
}
