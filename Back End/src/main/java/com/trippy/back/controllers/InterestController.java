package com.trippy.back.controllers;

import com.trippy.back.entities.FoundFlight;
import com.trippy.back.entities.Interest;
import com.trippy.back.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/interest")
public class InterestController {
    @Autowired
    TripService tripService;


    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(value = "/save")
    public void saveFlight(@RequestHeader(value = "email")String email, @RequestHeader(value = "list")String list, @RequestBody Interest interest){
        tripService.saveInterest(email,list,interest);
    }
}
