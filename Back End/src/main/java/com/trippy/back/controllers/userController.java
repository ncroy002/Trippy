package com.trippy.back.controllers;

import antlr.StringUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.trippy.back.entities.User;
import com.trippy.back.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping(value = "/user")
public class userController {
    @Autowired
    UserService userService = new UserService();

    @JsonFormat
    @ResponseBody
    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody User user){
        String token = userService.login(user);

        if(token.isEmpty()){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        else{
            return new ResponseEntity<>(
                    token, HttpStatus.OK
            );
        }
    }

}
