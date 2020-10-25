package com.trippy.back.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.trippy.back.entities.Help;
import com.trippy.back.services.HelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


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

    @GetMapping (value = "/listHelps")
    public ResponseEntity list(){
        return new ResponseEntity(helpservice.getAllHelps(), HttpStatus.OK);
    }


    @GetMapping(value = "/getHelp")
    public ResponseEntity getHelp(){
        return new ResponseEntity<>(helpservice.getAllHelps().get(0), HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteHelp") @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteHelp(@RequestParam(name="ID") Long ID){
        helpservice.deleteHelp(ID);
        return new ResponseEntity(HttpStatus.OK);
    }
    /*
    @PostMapping(value = "/updateHelp") @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity updateHelp(@RequestParam(name="ID") Long ID){
        helpservice.updateHelp(ID);
        return new ResponseEntity(HttpStatus.OK);
    }
*/
}

