package com.trippy.back.services;

import com.trippy.back.entities.User;
import com.trippy.back.repos.UserRepo;
import com.trippy.back.security.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    JwtGenerator jwtGenerator;
     private PasswordEncoder passwordEncoder;


    public String login(User user){
       User onFileUser = userRepo.findByEmail(user.getEmail());
       if(onFileUser != null){
           if(passwordEncoder.matches(user.getPassword(), onFileUser.getPassword())){
               String token = jwtGenerator.generate(onFileUser);
               onFileUser.setToken(token);
               userRepo.save(onFileUser);
               return token;
           }
       }
       //todo: delete this
        return "delete";
    }
    public List<User> getAllUsers(){
        return userRepo.findAll();
    }
    //todo: potentially return http status or confirmation
    public void deleteUser(Long id){
        if(userRepo.existsById(id)){
            userRepo.deleteById(id);
        }
    }
    public String findEmail(Long id){
        //checking if user's id exist in the database table of "user". If it does then return the user's corresponding email. if not return null.
        String email = null;
        try{
            if(userRepo.existsById(id)){
                User user = userRepo.getOne(id);
                email = user.getEmail();
            }
            else{
                email = null;
            }
        } catch (Exception e){
            System.out.println(e.getStackTrace());
        }
        return email;
    }

    public ResponseEntity createUser(User user){
        if(userRepo.findByEmail(user.getEmail()) != null ){
            return new ResponseEntity<>(HttpStatus.IM_USED);
        }
        else{
            userRepo.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }

}
