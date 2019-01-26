package com.driver.web.model;

import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Data
@Getter
public class LocationRequest {
    @NotNull(message = "Must provide latitude")
    private Double latitude;

    @NotNull(message = "Must provide longitude")
    private Double longitude;

    @NotNull(message = "Must provide accuracy")
    private Float accuracy;
}
