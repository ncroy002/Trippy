package com.trippy.back.controllers;

import com.trippy.back.entities.Flight;
import com.trippy.back.entities.Trip;
import com.trippy.back.services.FlightService;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping(value = "/flight")
public class FlightController {
    @Autowired
    FlightService flightService;

    @RequestMapping(value="/search/external/site")
    String searchFlight(@RequestBody Flight flight) throws IOException, ParseException {

        String flightID= flightService.generateSearchID(flight);
        //List<FlightPriceResult> flightPriceResults = flightService.searchResults(flightID);
        return flightService.searchResults(flightID).string();
    }

    @RequestMapping(value = "/find/airports")
    JSONObject findAirports(@RequestParam(name="city1") String city1, @RequestParam(name="city2") String city2) throws IOException {
        Flight flight = new Flight(city1, city2);
        Trip trip = new Trip(flight);
        return flightService.getAirports(trip);
    }

    @RequestMapping(value = "/browse/routes")
    String  browse(@RequestBody Flight flight) throws IOException {
        return flightService.browseRoutes(flight);
    }
}
