package com.trippy.back.wrappers;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.trippy.back.entities.Airport;

import java.util.Arrays;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ListPlaces {
    Places places;

    public Places getPlaces() {
        return places;
    }

    public void setPlaces(Places places) {
        this.places = places;
    }

    public ListPlaces(Places places) {
        this.places = places;
    }
}
