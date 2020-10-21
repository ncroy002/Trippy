package com.trippy.back.services;

import com.trippy.back.entities.Account;
import com.trippy.back.entities.FoundFlight;
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

    public List<FoundFlight> getAllTrips(String email){
        Account account= userRepo.findByEmail(email);
        return account.getTrips();
    }

    public void saveTrip(String email, FoundFlight foundFlight) {
        Account account= userRepo.findByEmail(email);
        List<FoundFlight> tripList = account.getTrips();
        tripList.add(foundFlight);
        //this save will also insert into the found_trip table
        userRepo.save(account);
    }
}
