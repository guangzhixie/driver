package com.driver.web.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;


@Data
@EqualsAndHashCode(callSuper = false)
public class LocationResponse extends BaseResponse<ErrorResponse> {
    @Builder
    public LocationResponse(HttpStatus httpStatus, ErrorResponse errorResponse) {
        super(httpStatus, errorResponse);
    }

}
