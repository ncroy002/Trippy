package com.trippy.back.entities;

public class Trip {
    final String country = "US";
    final String currency = "USD";
    final String locale = "en-US";

    //todo: delete out of flight and airport
    String cityId;

    Flight flight;


    public String getCountry() {
        return country;
    }

//    public void setCountry(String country) {
//        this.country = country;
//    }

    public String getCurrency() {
        return currency;
    }

//    public void setCurrency(String currency) {
//        this.currency = currency;
//    }

    public String getLocale() {
        return locale;
    }

//    public void setLocale(String locale) {
//        this.locale = locale;
//    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Trip(Flight flight) {
        this.flight = flight;
    }

    public Trip() {
    }
}
