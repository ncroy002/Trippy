package com.trippy.back.controllers;

import com.trippy.back.entities.Flight;
import com.trippy.back.entities.FoundTrip;
import com.trippy.back.entities.Trip;
import com.trippy.back.services.FlightService;
import com.trippy.back.services.TripService;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/flight")
public class FlightController {
    @Autowired
    FlightService flightService;
    @Autowired
    TripService tripService;

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
    String  browse(@RequestParam(name="city1") String city1, @RequestParam(name="city2") String city2, @RequestParam(name="date1") String date1, @RequestParam(name="date2") String date2) throws IOException {
        Flight flight = new Flight(city1, city2, date1, date2);
        return flightService.browseRoutes(flight);
    }

   // @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(value = "/save")
    void saveTrip(@RequestHeader(value = "email")String email, @RequestBody FoundTrip foundTrip){
        tripService.saveTrip(email,foundTrip);
    }

    @GetMapping("/save/view/all")
 //   @PreAuthorize("hasRole('ROLE_USER')")
    public List<FoundTrip> getAllSaves(@RequestHeader(value = "email") String email) {
        return tripService.getAllTrips(email);
    }
}
