package com.trippy.back.controllers;

import com.trippy.back.entities.Flight;
import com.trippy.back.entities.FlightUrlResult;
import com.trippy.back.entities.FoundFlight;
import com.trippy.back.entities.Trip;
import com.trippy.back.enumeration.Site;
import com.trippy.back.services.FlightService;
import com.trippy.back.services.TripService;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/flight")
public class FlightController {
    @Autowired
    FlightService flightService;
    @Autowired
    TripService tripService;

    //todo: might need to specify a flight as well to set flight object within trip.
    @RequestMapping(value="/search/external/site")
    List<FlightUrlResult> searchFlight(@RequestParam (name = "lapInfant") int lapInfant, @RequestParam int infant, @RequestParam int child, @RequestParam String city1, @RequestParam String city2, @RequestParam int youth, @RequestParam int flightType, @RequestParam int adults, @RequestParam int cabin, @RequestParam int seniors, @RequestParam String date1, @RequestParam String date2 ) throws IOException, ParseException {
        Flight flight = new Flight(lapInfant, infant, child, youth, flightType, adults, cabin, seniors);
        Trip trip = new Trip(city1, city2,date1, date2);
        trip.setFlight(flight);
        String flightID= flightService.generateSearchID(trip);
        //List<FlightPriceResult> flightPriceResults = flightService.searchResults(flightID);
        return flightService.searchResults(flightID);
    }
    @RequestMapping(value="/generate/url")
    List<String> url(@RequestParam String city1, @RequestParam String city1ID, @RequestParam String city2, @RequestParam String city2ID,@RequestParam String cabin, @RequestParam int children, @RequestParam int adults, @RequestParam int seniors, @RequestParam String date1, @RequestParam(required = false) String date2 ){
        List<String> urlStrings = new ArrayList<>();
        FlightUrlResult flightUrlResult = new FlightUrlResult(city1, city1ID, city2, city2ID, cabin, children, adults, seniors,date1, date2);
        for(Site site: flightUrlResult.getSiteList()){
            flightUrlResult.setSite(flightService.determineSite(site));
            urlStrings.add(flightService.generateUrl(flightUrlResult));
        }
        return urlStrings;
    }

    @RequestMapping(value = "/find/airports")
    JSONObject findAirports(@RequestParam(name="city1") String city1, @RequestParam(name="city2") String city2) throws IOException {
        Trip trip = new Trip(city1, city2);
        return flightService.getAirports(trip);
    }

    @RequestMapping(value = "/browse/routes")
    String  browse(@RequestParam(name="city1") String city1, @RequestParam(name="city2") String city2, @RequestParam(name="date1") String date1, @RequestParam(name="date2") String date2) throws IOException {
        Trip trip = new Trip(city1, city2, date1, date2);
        return flightService.browseRoutes(trip);
    }

   // @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(value = "/save")
    void saveTrip(@RequestHeader(value = "email")String email, @RequestBody FoundFlight foundFlight){
        tripService.saveTrip(email, foundFlight);
    }

    @GetMapping("/save/view/all")
 //   @PreAuthorize("hasRole('ROLE_USER')")
    public List<FoundFlight> getAllSaves(@RequestHeader(value = "email") String email) {
        return tripService.getAllTrips(email);
    }
}
