package com.trippy.back.entities;

import javax.persistence.*;

@Entity(name = "hotelRating")
public class HotelRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    //todo: drop id, set hotelID userID PK, FKs
    @Column
    int hotelID;

    @Column
    int userID;

    @Column
    int rating;

    public int getID() {
        return id;
    }
    public void setID(int id) {
        this.id = id;
    }
    public int getHotelID() {
        return hotelID;
    }
    public void setHotelID(int hotelID){
        this.hotelID = hotelID;
    }
    public int getUserID() {
        return userID;
    }
    public void setUserID(int uID) {
        this.userID = uID;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }

    public HotelRating() {}
    public HotelRating(int ID, int hID, int uID, int rating) {
        this.id = ID;
        this.hotelID = hID;
        this.userID = uID;
        this.rating = rating;
    }
}
