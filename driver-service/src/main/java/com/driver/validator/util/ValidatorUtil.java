package com.driver.validator.util;


public class ValidatorUtil {

    //TODO: move to centralized config
    private static final int LATITUDE_RANGE = 90;
    private static final int LONGITUDE_RANGE = 180;

    public static boolean isValidLatitude(Double latitude) {
        return latitude != null && latitude >= -LATITUDE_RANGE && latitude <= LATITUDE_RANGE;
    }

    public static boolean isValidLongitude(Double longitude) {
        return longitude != null && longitude >= -LONGITUDE_RANGE && longitude <= LONGITUDE_RANGE;
    }

}
