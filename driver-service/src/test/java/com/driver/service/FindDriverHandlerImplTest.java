package com.driver.service;

import com.driver.cache.DriverLocationCache;
import com.driver.model.DriverLocation;
import com.driver.model.LatLang;
import com.driver.service.impl.DistanceCalculatorImpl;
import com.driver.service.impl.FindDriverHandlerImpl;
import com.driver.web.model.FindDriverRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FindDriverHandlerImplTest {
    private static final int LIMIT = 1;

    private static final LatLang BASE_POINT = new LatLang(12.97161923, 77.59463452);
    private static final LatLang FAR_BASE_POINT = new LatLang(12.97161923, 70);

    private static final LatLang NEAR_POINT = new LatLang(12.97161923, 77.59464460);
    private static final LatLang FAR_POINT = new LatLang(10, 77.59464460);

    private static final int BASE_POINT_ID = 1;
    private static final int NEAR_POINT_ID = 2;
    private static final int FAR_POINT_ID = 3;

    @Mock
    private DriverLocationCache driverLocationCache;

    @Spy
    private DistanceCalculatorImpl distanceCalculator;

    @InjectMocks
    private FindDriverHandlerImpl findDriverHandler;

    @Before
    public void setup() {
        when(driverLocationCache.getAllCurrentDriverLocations()).thenReturn(mockLocationCacheValues());
    }

    private ConcurrentMap<Integer, LatLang> mockLocationCacheValues() {
        ConcurrentMap<Integer, LatLang> mockCache = new ConcurrentHashMap<>(3);
        mockCache.put(BASE_POINT_ID, BASE_POINT);
        mockCache.put(NEAR_POINT_ID, NEAR_POINT);
        mockCache.put(FAR_POINT_ID, FAR_POINT);
        return mockCache;
    }

    @Test
    public void testWhenAbleToFindDriverWithDefaultLimit() {
        List<DriverLocation> locations = findDriverHandler.handle(new FindDriverRequest(BASE_POINT.getLatitude(), BASE_POINT.getLongitude(), null,null));
        assertEquals(2, locations.size());
    }

    @Test
    public void testWhenAbleToFindDriverWithSpecificLimit() {
        List<DriverLocation> locations = findDriverHandler.handle(new FindDriverRequest(BASE_POINT.getLatitude(), BASE_POINT.getLongitude(), null,LIMIT));
        assertEquals(1, locations.size());
    }

    @Test
    public void testWhenNotAbleToFindDriver() {
        List<DriverLocation> locations = findDriverHandler.handle(new FindDriverRequest(FAR_BASE_POINT.getLatitude(), FAR_BASE_POINT.getLongitude(), null, null));
        assertEquals(0, locations.size());
    }

}
