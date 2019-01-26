package com.driver.web.resource;

import com.driver.web.model.FindDriverRequest;
import com.driver.web.model.LocationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

public interface DriverResource {

    @RequestMapping(value = "/drivers/{id}/location", method = PUT, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity updateLocation(@PathVariable int id, @RequestBody @Valid LocationRequest locationRequest);

    @RequestMapping(value = "/drivers", method = GET, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity findDriver(@RequestBody @Valid FindDriverRequest findDriverRequest);
}
