package com.trippy.back.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.trippy.back.entities.Forums;
import com.trippy.back.services.ForumsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/forums")
public class ForumsController {
    @Autowired
    ForumsService forumsservice;

    @JsonFormat
    @ResponseBody
    @PostMapping(value = "/newForums")
    public ResponseEntity newForums(@RequestBody Forums forums){


        forumsservice.addForums(forums);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping(value = "/getForums")
    public ResponseEntity getForums(){
        return new ResponseEntity<>(forumsservice.findAllForums().get(0), HttpStatus.OK);
    }

}

