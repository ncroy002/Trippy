package com.trippy.back.controllers;
import com.trippy.back.entities.Hotels;
import com.trippy.back.entities.Trip;
import com.trippy.back.services.RandomTripService;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping(value = "/randomTrip")
public class RandomTripController {
    @Autowired
    RandomTripService randomTripService;

    @RequestMapping(value = "/test")
    Object[] returnBody(@RequestParam(name = "randomNum")int random) throws IOException {
        return randomTripService.findTrip(random);
    }
}
