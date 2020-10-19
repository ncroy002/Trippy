package com.trippy.back.entities;

import javax.persistence.*;

@Entity(name = "eventRating")
public class EventRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    //todo: drop id, set eventID userID PK, FKs
    @Column
    int eventID;

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
    public int getEventID() {
        return eventID;
    }
    public void setEventID(int eID){
        this.eventID = eID;
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

    public EventRating() {}
    public EventRating(int ID, int eID, int uID, int rating) {
        this.id = ID;
        this.eventID = eID;
        this.userID = uID;
        this.rating = rating;
    }
}
