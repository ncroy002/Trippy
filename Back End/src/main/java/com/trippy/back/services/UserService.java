package com.trippy.back.services;

import com.trippy.back.entities.Account;
import com.trippy.back.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public List<Account> findAll(){
        return userRepo.findAll();
    }

    public void deleteUser(Long ID){
        if(userRepo.existsById(ID)){
            userRepo.deleteById(ID);
        }
    }

}
