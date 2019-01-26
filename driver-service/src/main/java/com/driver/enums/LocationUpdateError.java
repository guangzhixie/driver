package com.driver.enums;

public enum LocationUpdateError {
    INVALID_ID("Driver ids should between 1 to 50000"), INVALID_LATITUDE("Latitude should be between +/- 90"), INVALID_LONGITUDE("Longitude should be between +/- 180");

    private String message;

    LocationUpdateError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}