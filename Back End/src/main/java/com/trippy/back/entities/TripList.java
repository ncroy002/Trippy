package com.trippy.back.entities;

import javax.persistence.*;
import java.util.List;
 @Entity
public class TripList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(unique = true)
    String name;
    @ManyToMany(targetEntity = Hotels.class,cascade = CascadeType.ALL )
    List<Hotels> hotels;
    @ManyToMany(targetEntity = FoundFlight.class,cascade = CascadeType.ALL )
    List<FoundFlight> flights;

    @ManyToOne
    @JoinColumn(name="account_id", nullable=false)
    private Account account;

     public TripList() {
     }

     public Long getId() {
         return id;
     }

     public void setId(Long id) {
         this.id = id;
     }

     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     public List<Hotels> getHotels() {
         return hotels;
     }

     public void setHotels(List<Hotels> hotels) {
         this.hotels = hotels;
     }

     public List<FoundFlight> getFlights() {
         return flights;
     }

     public void setFlights(List<FoundFlight> flights) {
         this.flights = flights;
     }

     public Account getAccount() {
         return account;
     }

     public void setAccount(Account account) {
         this.account = account;
     }
 }
