package com.trippy.back.controllers;

import com.trippy.back.repos.PasswordResetTokenRepo;
import com.trippy.back.repos.UserRepo;
import com.trippy.back.services.FaqService;
import com.trippy.back.services.PasswordResetTokenService;
import com.trippy.back.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "/forgot")
public class PasswordResetTokenController {
    @Autowired
    PasswordResetTokenService passwordResetTokenService;
    @Autowired
    UserService userService;
    @Autowired
    PasswordResetTokenRepo passwordResetTokenRepo;
    @Autowired
    UserRepo userRepo;
}
