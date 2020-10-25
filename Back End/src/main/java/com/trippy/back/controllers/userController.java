package com.trippy.back.controllers;

import com.trippy.back.entities.Account;
import com.trippy.back.entities.PasswordResetToken;
import com.trippy.back.payload.request.LoginRequest;
import com.trippy.back.payload.request.SignupRequest;
import com.trippy.back.payload.response.JwtResponse;
import com.trippy.back.payload.response.MessageResponse;
import com.trippy.back.repos.UserRepo;
import com.trippy.back.security.JwtUtils;
import com.trippy.back.security.services.UserDetailsImpl;
import com.trippy.back.services.EmailService;
import com.trippy.back.services.PasswordResetTokenService;
import com.trippy.back.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
//@CrossOrigin
@RestController
@RequestMapping(value = "/user")
public class userController {
    @Autowired
    private UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepo userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    PasswordResetTokenService passwordResetTokenService;

    @Autowired
    EmailService emailService;

    @GetMapping (value = "/listUsers") @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity listUsers(){
        return new ResponseEntity(userService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteUser") @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteUser(@RequestParam(name="ID") Long ID){
        userService.deleteUser(ID);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping (value = "/resetPassword") @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity resetPassword(@RequestParam(name="ID") Long ID, @RequestParam(name="password") String password) {
        userService.resetPassword(ID, password);
        return new ResponseEntity(HttpStatus.OK);
    }

    //Send Password reset email to user
    @PostMapping(value="/forgotPassword")
    public ResponseEntity resetPassword(@RequestParam("email") String userEmail){
        Account account = userRepository.findByEmail(userEmail);
        if (account == null){
            return new ResponseEntity(HttpStatus.IM_USED);
        }
        String token = UUID.randomUUID().toString();
        passwordResetTokenService.createPasswordResetToken(account, token);
        emailService.sendEmail(passwordResetTokenService.constructResetTokenEmail(token, account));
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value="/resetPasswordByUser")
    public ResponseEntity passwordResetByToken(@RequestParam("token") String token, @RequestParam("password") String password){
        PasswordResetToken passwordResetToken = passwordResetTokenService.getPasswordResetToken(token);

        Optional<Account> account = passwordResetTokenService.getUserByPasswordResetToken(token);
        if (account == null){
            return new ResponseEntity(HttpStatus.IM_USED);
        }
        userService.resetPassword(account.get().getId(), password);
        return new ResponseEntity(HttpStatus.OK);
    }





    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody LoginRequest req){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping(value = "/create")
    public ResponseEntity create(@RequestBody SignupRequest req){
        try {
            if (userRepository.existsByEmail(req.getEmail())) {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: Email is already taken!"));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        Account user = new Account(req.getEmail(),
                req.getFirstName(),
                req.getLastName(),
                encoder.encode(req.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    //@PostMapping(value = "/update")
    //public ResponseEntity update(@RequestBody Account account){
    //    userService.update(account);
    //    return new ResponseEntity<>(HttpStatus.OK);
    //}

    @RequestMapping(value = "/secure/getuser")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity getUser(){
        return new ResponseEntity<>(userRepository.findAll().get(0), HttpStatus.OK);
    }
}