package com.driver.main;

import com.driver.web.model.FindDriverRequest;
import com.driver.web.model.LocationRequest;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertEquals;

@ContextConfiguration(classes = {DriverServiceITApplication.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class DriverServiceStepDef {

    private static final String HOST = "http://localhost:8080";

    private RestTemplate restTemplate = new RestTemplate();

    private HttpStatus actualHttpStatus;

    @When("^update location for driver (\\d+)$")
    public void updateLocation(int id, List<LocationRequest> locationRequests) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<LocationRequest> entity = new HttpEntity<>(locationRequests.get(0), headers);
        try {
            ResponseEntity response = restTemplate.exchange(HOST + "/drivers/" + id + "/location", HttpMethod.PUT, entity, Object.class);
            actualHttpStatus = response.getStatusCode();
        } catch (final HttpClientErrorException e) {
            actualHttpStatus = e.getStatusCode();
        }
    }

    @When("^find driver$")
    public void findDriver(List<FindDriverRequest> findDriverRequests) {
        FindDriverRequest request = findDriverRequests.get(0);
        try {
            ResponseEntity response = restTemplate.getForEntity(HOST + "/drivers?latitude="+request.getLatitude()+"&longitude="+request.getLongitude(), Object.class);
            actualHttpStatus = response.getStatusCode();
        } catch (final HttpClientErrorException e) {
            actualHttpStatus = e.getStatusCode();
        }
    }

    @Then("^response status code should be (\\d+)$")
    public void verifyStatusCode(int expectedStatusCode) {
        assertEquals(expectedStatusCode, actualHttpStatus.value());
    }
}
