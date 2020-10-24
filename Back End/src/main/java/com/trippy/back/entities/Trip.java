package com.trippy.back.entities;


public class Trip {
    final String country = "US";
    final String currency = "USD";
    final String locale = "en-US";


    //todo: delete out of flight and airport
    String cityId;

    //todo: ignore this field and get it from trips in the future
    //Departure City IATA Code. ie. NYC
    String city1;
    //todo: ignore this field and get it from trips in the future
    //Destination City IATA Code. ie. NYC
    String city2;
    //todo: add this to trip
    //Departure Date (YYYY-MM-DD)
    //Date date1;
    String date1;
    //Return Date (YYYY-MM-DD)
    String date2;

    Flight flight;
    Hotels hotel;


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

    public String getCity1() {
        return city1;
    }

    public void setCity1(String city1) {
        this.city1 = city1;
    }

    public String getCity2() {
        return city2;
    }

    public void setCity2(String city2) {
        this.city2 = city2;
    }

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public String getDate2() {
        return date2;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }

    public Trip(Flight flight) {
        this.flight = flight;
    }

    public Trip(String city1, String city2) {
        this.city1 = city1;
        this.city2 = city2;
    }

    public Trip(String city1, String city2, String date1, String date2) {
        this.city1 = city1;
        this.city2 = city2;
        this.date1 = date1;
        this.date2 = date2;
    }

    public Trip() {
    }
}
