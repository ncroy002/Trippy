package com.trippy.back.entities;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/*
Author: Tommie Walker
    Contact me for any questions regarding this class, Thanks!
    PS: The api also requires a boolean of "islive "to be "true" to generate a search id. This should be hard coded
 */
//All comments/requirements correspond with the variable listed below said comment
public class Flight {
    //_______________________________________________________________
    //REQUIRED VARIABLES
    //---------------------------------------------------------------
    //number of lap infants in lap (under 2 years old)
    //limit: 0-9
    int lapInfant;
//    nineRange lapInfant;

    //number of infants in lap (under 2 years old)
    //limit: 0-9
    int infant;
//    nineRange infant;

    //number of children between the ages of 2-11
    //limit: 0-9
    int child;
//    nineRange child;

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
    //number of youth (ages of 12-17)
    //limit: 0-9
    int  youth;
//    nineRange youth;

    /*
    Type of flight:
    1= one-way trip
    2= round trip
     */
    int flightType;
    //number of adults age 18+
    //limit 0-9
    int adults;
//    nineRange adults;

    /*
    1 = economy
    2 = business
    3 = first
    5 = premium economy
     */
    int cabin;
    //number of senior citizens
    //allowed 9 values
    int seniors;
    //Search ID will be populated after the initial id generation, id will be used to get list of prices
    //id to get results after initial api call is conducted
    String SearchID;

    //__________________________________________________________
    //OPTIONAL VARIABLES
    //----------------------------------------------------------
    //todo: add this to trip
    //Return Date (YYYY-MM-DD)
    String date2;
    //Date date2;


//    JsonNode details;

    public Flight() {
    }

    public Flight(String city1, String city2) {
        this.city1 = city1;
        this.city2 = city2;
    }

    public Flight(int lapInfant, int infant, int child, String city1, String city2, String date1, int youth, int flightType, int adults, int cabin, int seniors) {
        this.lapInfant = lapInfant;
        this.infant = infant;
        this.child = child;
        this.city1 = city1;
        this.city2 = city2;
        this.date1 = date1;
        this.youth = youth;
        this.flightType = flightType;
        this.adults = adults;
        this.cabin = cabin;
        this.seniors = seniors;
    }

    public Flight(String city1, String city2, String date1, String date2) {
        this.city1 = city1;
        this.city2 = city2;
        this.date1 = date1;
        this.date2 = date2;
    }

    public int getLapInfant() {
        return lapInfant;
    }

    public void setLapInfant(int lapInfant) {
        this.lapInfant = lapInfant;
    }

    public int getInfant() {
        return infant;
    }

    public void setInfant(int infant) {
        this.infant = infant;
    }

    public int getChild() {
        return child;
    }

    public void setChild(int child) {
        this.child = child;
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

    public int getYouth() {
        return youth;
    }

    public void setYouth(int youth) {
        this.youth = youth;
    }

    public int getFlightType() {
        return flightType;
    }

    public void setFlightType(int flightType) {
        this.flightType = flightType;
    }

    public int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public int getCabin() {
        return cabin;
    }

    public void setCabin(int cabin) {
        this.cabin = cabin;
    }

    public int getSeniors() {
        return seniors;
    }

    public void setSeniors(int seniors) {
        this.seniors = seniors;
    }

    public String getSearchID() {
        return SearchID;
    }

    public void setSearchID(String searchID) {
        SearchID = searchID;
    }

    public String getDate2() {
        return date2;
    }
    public String dateToString(Date date){
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            String strDate = dateFormat.format(date);
            return strDate;
    }

    public void setDate2(String date) {
        this.date2 = date;
    }

//    public JsonNode getDetails() {
//        return details;
//    }
//
//    public void setDetails(JsonNode details) {
//        this.details = details;
//    }


    public Flight(int lapInfant, int infant, int child, String city1, String city2, String date1, int youth, int flightType, int adults, int cabin, int seniors, String date2) {
        this.lapInfant = lapInfant;
        this.infant = infant;
        this.child = child;
        this.city1 = city1;
        this.city2 = city2;
        this.date1 = date1;
        this.youth = youth;
        this.flightType = flightType;
        this.adults = adults;
        this.cabin = cabin;
        this.seniors = seniors;
        this.date2 = date2;
    }
}
