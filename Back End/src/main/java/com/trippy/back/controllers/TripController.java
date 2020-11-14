package com.trippy.back.controllers;

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


}
