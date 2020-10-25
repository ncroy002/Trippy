package com.trippy.back.controllers;

import com.trippy.back.entities.Flight;
import com.trippy.back.entities.Trip;
import com.trippy.back.services.FilterService;
import com.trippy.back.services.FlightService;
import com.trippy.back.services.TripService;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/filter")

public class filterController {
    @Autowired
    FilterService filterService;

    @RequestMapping(value="/interests")
    public ResponseEntity filterInterests(@RequestParam(name="destination") String destination, @RequestParam(name="interest") String interest) throws IOException{
        //String response = filterService.generateSearchID();
        return new ResponseEntity(filterService.generateInterestResults(destination, interest), HttpStatus.OK);
    }
}
