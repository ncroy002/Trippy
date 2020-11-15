package com.trippy.back.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Interest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    String state;
    String county;
    String address;

    @ManyToMany(mappedBy = "interests")
    private List<TripList> tripLists;

    public Interest() {
    }
    public Interest(String name, String state, String county, String address){
        this.name= name;
        this.county = county;
        this.state = state;
        this.address = address;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
