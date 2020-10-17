package com.trippy.back.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.trippy.back.entities.Faq;
import com.trippy.back.services.FaqService;
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
@RequestMapping(value = "/faq")
public class faqController {
    @Autowired
    FaqService faqservice = new FaqService();

    @JsonFormat
    @ResponseBody
    @PostMapping(value = "/newFaq")
    public ResponseEntity newFaq(@RequestBody Faq faq){


        faqservice.addFaq(faq);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping (value = "/listFaqs") @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity listUsers(){
        return new ResponseEntity(faqservice.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/getFaq")
    public ResponseEntity getFaq(){
        return new ResponseEntity<>(faqservice.getAllFaqs().get(0), HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteFaq") @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteFaq(@RequestParam(name="ID") Long ID){
        faqservice.deleteFaq(ID);
        return new ResponseEntity(HttpStatus.OK);
    }

}

