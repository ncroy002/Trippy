package com.trippy.back.services;

import com.trippy.back.BackApplication;
import com.trippy.back.entities.Account;
import com.trippy.back.entities.Event;
import com.trippy.back.repos.EventsRepo;
import com.trippy.back.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    EventsRepo eventsRepo;

    public List<Account> findAll(){
        return userRepo.findAll();
    }

    public void deleteUser(Long ID){
        if(userRepo.existsById(ID)){
            userRepo.deleteById(ID);
        }
    }

    public void resetPassword(Long ID, String password) {
        try {
            if (userRepo.existsById(ID)) {
                Account account = userRepo.getOne(ID);
                BackApplication.LOGGER.info("Resetting password for userID: " + account.getId());
                account.setPassword(encoder.encode(password));
                userRepo.save(account);
                BackApplication.LOGGER.info("Password reset");
                return;
            }
        }
        catch(Exception e){
                BackApplication.LOGGER.log(Level.SEVERE, "Failed to updated password", e);

            }
        }

    public Account followEvent(String email, int eventId){
        Optional<Event> foundEvent = eventsRepo.findById(eventId);

        if(foundEvent.isPresent()){
            Event event = foundEvent.get();
            Account account = userRepo.findByEmail(email);
            List<Event> foundEvents = account.getEvents();
            foundEvents.add(event);
            return userRepo.save(account);
        }

        throw new RuntimeException("Failed to save event [" + eventId + "]");
    }

    public List<Event> findEvents(String email){
        Account account = userRepo.findByEmail(email);
        return account.getEvents();
    }
}



