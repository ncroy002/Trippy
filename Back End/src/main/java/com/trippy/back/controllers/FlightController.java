package com.trippy.back.controllers;

import com.trippy.back.entities.Flight;
import com.trippy.back.services.FlightService;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping(value = "/flight")
public class FlightController {
    @Autowired
    FlightService flightService;

    @RequestMapping(value="/search")
    String searchFlight(@RequestBody Flight flight) throws IOException, ParseException {

        String flightID= flightService.generateSearchID(flight);
        //List<FlightPriceResult> flightPriceResults = flightService.searchResults(flightID);
        return flightService.searchResults(flightID).string();
    }
}
