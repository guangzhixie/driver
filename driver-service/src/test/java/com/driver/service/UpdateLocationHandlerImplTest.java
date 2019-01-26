package com.driver.service;

import com.driver.cache.DriverLocationCache;
import com.driver.model.LatLang;
import com.driver.service.impl.UpdateLocationHandlerImpl;
import com.driver.web.model.LocationRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UpdateLocationHandlerImplTest {
    private static final int TEST_ID = 1;
    private static final double TEST_LATITUDE = 10;
    private static final double TEST_LONGITUDE = 100;

    @Mock
    private DriverLocationCache driverLocationCache;

    @Mock
    private LocationRequest locationRequest;

    @InjectMocks
    private UpdateLocationHandlerImpl updateLocationHandler;

    @Before
    public void setup() {
        when(locationRequest.getLatitude()).thenReturn(TEST_LATITUDE);
        when(locationRequest.getLatitude()).thenReturn(TEST_LONGITUDE);
    }

    @Test
    public void testUpdateLocation() {
        updateLocationHandler.handle(TEST_ID, locationRequest);
        verify(driverLocationCache).updateLocation(TEST_ID, new LatLang(locationRequest.getLatitude(), locationRequest.getLongitude()));
    }
}
