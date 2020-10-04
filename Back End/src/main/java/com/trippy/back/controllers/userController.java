package com.trippy.back.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.trippy.back.entities.Account;
import com.trippy.back.services.UserService;
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
@RequestMapping(value = "/user")
public class userController {
    @Autowired
    UserService userService = new UserService();

    @JsonFormat
    @ResponseBody
    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody Account account){
        String token = userService.login(account);

        if(token.isEmpty()){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        else{
            Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)    SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            authorities.forEach(System.out::println);
            return new ResponseEntity<>(
                    token, HttpStatus.OK
            );
        }
    }

    @PostMapping(value = "/create")
    public ResponseEntity create(@RequestBody Account res){

        Account newAccount = new Account(res.getEmail(), res.getPassword(), res.getFirstName(), res.getLastName());

        userService.createUser(newAccount);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping(value = "/update")
    public ResponseEntity update(@RequestBody Account account){
        userService.update(account);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/secure/getuser")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity getUser(){
        return new ResponseEntity<>(userService.getAllUsers().get(0), HttpStatus.OK);
    }


}
