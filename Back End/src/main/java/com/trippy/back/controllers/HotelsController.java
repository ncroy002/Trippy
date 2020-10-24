package com.trippy.back.controllers;
import com.trippy.back.entities.Hotels;
import com.trippy.back.entities.Trip;
import com.trippy.back.services.HotelsService;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping(value = "/hotels")

public class HotelsController {
    @Autowired
    HotelsService hotelsService;

    @RequestMapping(value = "/test")
    Hotels[] returnBody(@RequestParam(name = "cityName")String cityName,@RequestParam(name = "date1")String date1,@RequestParam(name = "date2")String date2) throws IOException {
         return hotelsService.findHotel(cityName,date1,date2);
    }

    /*
    @RequestMapping(value = "/test")
    String returnBody(@RequestBody Trip trip) throws IOException {
        //return hotelsService.generateSearchID(trip);
        return hotelsService.findHotel(trip);
    }
*/
}
