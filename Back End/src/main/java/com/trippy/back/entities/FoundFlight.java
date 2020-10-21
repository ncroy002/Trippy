package com.trippy.back.entities;

import javax.persistence.*;
import java.util.*;

@Entity
public class FoundFlight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String city1ID;
    String city2ID;
    String city1Name;
    String city2Name;
    double minCost;
    String carrierName;
    Date saveDate;

    @ManyToMany(mappedBy = "trips")
    private List<Account> accounts;



    public String getCity1ID() {
        return city1ID;
    }

    public void setCity1ID(String city1ID) {
        this.city1ID = city1ID;
    }

    public String getCity2ID() {
        return city2ID;
    }

    public void setCity2ID(String city2ID) {
        this.city2ID = city2ID;
    }

    public String getCity1Name() {
        return city1Name;
    }

    public void setCity1Name(String city1Name) {
        this.city1Name= city1Name;
    }

    public String getCity2Name() {
        return city2Name;
    }

    public void setCity2Name(String city2Name) {
        this.city2Name = city2Name;
    }

    public double getMinCost() {
        return minCost;
    }

    public void setMinCost(double minCost) {
        this.minCost = minCost;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public Date getSaveDate() {
        return saveDate;
    }

    public void setSaveDate(Date saveDate) {
        this.saveDate = saveDate;
    }


    public FoundFlight(String city1Name, String city1ID , String city2Name , String city2ID , Date saveDate, double minCost, String carrierName) {
        this.city1ID = city1ID;
        this.city2ID = city2ID;
        this.city1Name = city1Name;
        this.city2Name = city2Name;
        this.minCost = minCost;
        this.carrierName = carrierName;
        this.saveDate = saveDate;
    }

    public FoundFlight() {
    }
}
