package com.trippy.back.controllers;

import com.trippy.back.entities.Trip;
import com.trippy.back.entities.TripList;
import com.trippy.back.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/trip")
public class TripController {
    @Autowired
    TripService tripService;

    @GetMapping("/get/all")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<String> getAll(@RequestHeader(value = "email")String email) {
        return tripService.getTripListsNames(email);
    }

    @PostMapping("/create")
    @PreAuthorize(("hasRole('ROLE_USER')"))
    public void createList(@RequestHeader(value= "email")String email, @RequestHeader(value= "trip_name") String tripName){
        tripService.createTripList(email, tripName);
    }

    @GetMapping("/get/trips")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<TripList> getAllTrips(@RequestParam(value = "email") String email){
         return tripService.getAllTripsInformation(email);
    }

    @DeleteMapping("/delete/trip")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String deleteTrip(@RequestParam(value="tripName") String tripName, @RequestParam(value = "userEmail") String userEmail){
        try{
            tripService.deleteTripList(tripName, userEmail);
            return "Deletion Successfully!";
        }catch (Exception ex){
            return ex.toString();
        }
    }

}
