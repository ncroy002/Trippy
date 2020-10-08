package com.trippy.back.wrappers;

import com.trippy.back.entities.Airport;

import java.util.ArrayList;
import java.util.List;

public class Places {
    Airport[] airports;

    public Airport[] getAirports() {
        return airports;
    }

    public void setAirports(Airport[] airports) {
        this.airports = airports;
    }

    public Places(Airport[] airports) {
        this.airports = airports;
    }

    @Override
    public String toString(){
        return "Places [airports="+airports+"]";
    }
}
