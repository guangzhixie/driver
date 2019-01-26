package com.driver.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindDriverRequest {
    private Double latitude, longitude;
    private Integer radius, limit;
}
