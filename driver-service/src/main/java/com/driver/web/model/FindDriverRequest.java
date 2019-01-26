package com.driver.web.model;

import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Data
@Getter
public class FindDriverRequest {
    @NotNull(message = "Must provide latitude")
    private Double latitude;

    @NotNull(message = "Must provide longitude")
    private Double longitude;

    private int radius, limit;
}
