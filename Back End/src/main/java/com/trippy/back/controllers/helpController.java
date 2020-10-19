package com.trippy.back.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.trippy.back.entities.Help;
import com.trippy.back.services.HelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping(value = "/help")
public class helpController {
    @Autowired
    HelpService helpservice = new HelpService();

    @JsonFormat
    @ResponseBody
    @PostMapping(value = "/newHelp")
    public ResponseEntity newHelp(@RequestBody Help help){


        helpservice.addHelp(help);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping(value = "/getHelp")
    public ResponseEntity getHelp(){
        return new ResponseEntity<>(helpservice.getAllHelps().get(0), HttpStatus.OK);
    }

}

