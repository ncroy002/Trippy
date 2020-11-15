package com.trippy.back.services;

import com.trippy.back.entities.Account;
import com.trippy.back.entities.FoundFlight;
import com.trippy.back.entities.Interest;
import com.trippy.back.entities.TripList;
import com.trippy.back.repos.FlightRepo;
import com.trippy.back.repos.InterestRepo;
import com.trippy.back.repos.TripListRepo;
import com.trippy.back.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TripService {
    @Autowired
    FlightRepo flightRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    TripListRepo tripListRepo;

    public List<TripList> getAllTrips(String email){
        Account account= userRepo.findByEmail(email);
        return account.getTrips();
    }

    public List<TripList> getAllTripsInformation(String email){
        ArrayList<TripList> foundTrips = new ArrayList<>();

        Account account = userRepo.findByEmail(email);
        List<TripList> savedTrips = account.getTrips();
        for(TripList trip: savedTrips){
            foundTrips.add(trip);
        }
        return foundTrips;
    }


    public void saveFlight(String email, String list, FoundFlight foundFlight) {
        Account account= userRepo.findByEmail(email);
        TripList tripList =  tripListRepo.findTripListByNameandAccount(list, account.getId());
        tripList.getFlights().add(foundFlight);
        tripListRepo.save(tripList);
    }
    public void saveInterest(String email, String list, Interest interest) {
        Account account= userRepo.findByEmail(email);
        TripList tripList =  tripListRepo.findTripListByNameandAccount(list, account.getId());
        tripList.getInterests().add(interest);
        tripListRepo.save(tripList);
    }

    public List<String> getTripListsNames(String email){
        List<String> tripListsNames = new ArrayList<String>();
        try{
            if(userRepo.existsByEmail(email)){
                List<TripList> tripLists = getAllTrips(email);
                for(TripList trip: tripLists){
                    tripListsNames.add(trip.getName());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    return tripListsNames;
    }

    //Insert new Trip List for particular user
    public void createTripList(String email, String listName){
        Account account= userRepo.findByEmail(email);
        TripList tripList = new TripList();
        tripList.setName(listName);
        tripList.setAccount(account);
        account.getTrips().add(tripList);
        userRepo.save(account);
    }

    public void deleteTripList(String tripName, String userEmail){
        Account account = userRepo.findByEmail(userEmail);
        TripList deleteTripList = tripListRepo.findTripListByNameandAccount(tripName, account.getId());
        tripListRepo.delete(deleteTripList);
    }


    public SimpleMailMessage sendFlightSummary(String email, FoundFlight savedFlight){
        Account account = userRepo.findByEmail(email);
        return constructEmail("Flight Summary", account, savedFlight);
    }

    private SimpleMailMessage constructEmail(String subject, Account account, FoundFlight savedFlight){
        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText("Flight Summary \n" +
                "Minimum Pricing: " + savedFlight.getMinCost() + "\n" +
                "Departure Location: " + savedFlight.getCity1Name() + "\n" +
                "Destination Location: " + savedFlight.getCity2Name() + "\n" +
                "Carrier: " + savedFlight.getCarrierName() + "\n" +
                "Saved Date: " + savedFlight.getSaveDate()
        );
        email.setTo(account.getEmail());
        email.setFrom("Trippy");
        return email;
    }
}
