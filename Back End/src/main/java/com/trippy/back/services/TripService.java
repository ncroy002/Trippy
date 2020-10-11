package com.trippy.back.services;

import com.trippy.back.entities.Account;
import com.trippy.back.entities.FoundTrip;
import com.trippy.back.repos.TripRepo;
import com.trippy.back.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripService {
    @Autowired
    TripRepo tripRepo;
    @Autowired
    UserRepo userRepo;

    public List<FoundTrip> getAllTrips(String email){
        Account account= userRepo.findByEmail(email);
        return account.getTrips();
    }

    public void saveTrip(String email, FoundTrip foundTrip) {
        Account account= userRepo.findByEmail(email);
        List<FoundTrip> tripList = account.getTrips();
        tripList.add(foundTrip);
        //this save will also insert into the found_trip table
        userRepo.save(account);
    }
}
