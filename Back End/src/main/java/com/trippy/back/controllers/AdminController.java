package com.trippy.back.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @GetMapping(value = "/test")
    public String test(){
        return "You are admin";
    }

}
