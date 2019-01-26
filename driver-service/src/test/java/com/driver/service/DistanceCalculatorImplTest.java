package com.driver.service;

import com.driver.model.LatLang;
import com.driver.service.impl.DistanceCalculatorImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class DistanceCalculatorImplTest {
    private static final double LATITUDE1 = 12.915700;
    private static final double LONGITUDE1 = 77.632046;
    private static final double LATITUDE2 = 11.665154;
    private static final double LONGITUDE2 = 78.145657;

    private static final double DELTA = 0.01;

    @InjectMocks
    private DistanceCalculatorImpl distanceCalculatorImpl;

    @Test
    public void testSamePointShouldHaveZeroDistance() {
        LatLang point1 = new LatLang(LATITUDE1, LONGITUDE1);
        LatLang point2 = new LatLang(LATITUDE1, LONGITUDE1);

        double distance = distanceCalculatorImpl.calculateDistanceInMeters(point1, point2);

        assertEquals(0, distance, DELTA);
    }

    @Test
    public void testDistanceForDiffPoints() {
        LatLang point1 = new LatLang(LATITUDE1, LONGITUDE1);
        LatLang point2 = new LatLang(LATITUDE2, LONGITUDE2);

        double distance = distanceCalculatorImpl.calculateDistanceInMeters(point1, point2);

        assertEquals(149825.5, distance, DELTA);
    }

}
