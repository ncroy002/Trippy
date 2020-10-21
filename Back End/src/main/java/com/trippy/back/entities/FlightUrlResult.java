package com.trippy.back.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trippy.back.enumeration.Site;

import java.util.ArrayList;
import java.util.List;

public class FlightUrlResult {
    String site;
    String url;
    Site[] siteList = Site.values();

    String flightType = "on";
    String mode = "search";
    String trip;
    String leg1;
    String option;
    String leg2;
    String passengers;
    String fromDate;
    String toDate;
    String d1;
    String d2;

    //variables that will need to be filled out to make the url
    String departureCityName;
    String departureCityAbbrev;
    String destinationCityName;
    String destinationCityAbbrev;
    String cabinClass;
    int children;
    int adults;
    int seniors;
    int month1;
    int day1;
    int year1;
    int month2;
    int day2;
    int year2;



    public FlightUrlResult(String site, String url) {
        this.site = site;
        this.url = url;
    }

    public FlightUrlResult() {
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getUrl() {
        url = "https://"+site+"/Flights-Search?flight-type="+flightType+"&mode="+mode+"&trip="+trip+"&leg1="+getLeg1()+"&options="+getOption()+"&leg2="+getLeg2()+"&passengers="+getPassengers()+"&fromDate="+getFromDate()+"&toDate="+getToDate()+"&d1="+getD1()+"&d2="+getD2();
        return  url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLeg1(){
        leg1 = "from%3A"+departureCityName+"+%28"+departureCityAbbrev+"+Airports%29%2Cto%3A"+destinationCityName+/*"%2C+"+CA+*/"+%28"+destinationCityAbbrev+"+Intl.%29%2Cdeparture%3A"+month1+"%2F"+day1+"%2F"+year1+"TANYT";
        return leg1;
    }
    public String getLeg2(){
        leg2 = "from%3A"+destinationCityName+/*"%2C+CA+*/"%28"+destinationCityAbbrev+"Intl.%29%2Cto%3A"+departureCityName+"+%28"+departureCityAbbrev+"+Airports%29%2Cdeparture%3A"+month2+"%2F"+day2+"%2F"+year2+"TANYT";
        return leg2;
    }
    public String getOption(){
        option = "cabinclass%3A"+cabinClass;
        return option;
    }
    public String getPassengers(){
        passengers = "children%3A"+children+"%2Cadults%3A"+adults+"%2Cseniors%3A"+seniors+"%2Cinfantinlap%3AY";
        return passengers;
    }

    public String getFromDate(){
        fromDate = month1 + "%2F"+day1+"%2F"+year1;
        return fromDate;
    }
    public String getToDate(){
        toDate = month2 + "%2F"+day2+"%2F"+year2;
        return toDate;
    }
    public String getD1(){
       // d1=year1+"-"+month1+"-"+day1;
        return d1;
    }

    public String getD2(){
        //d2= year2+"-"+month2+"-"+day2;
        return d2;
    }

    public void setDepartureCityName(String departureCityName) {
        this.departureCityName = departureCityName;
    }

    public void setDepartureCityAbbrev(String departureCityAbbrev) {
        this.departureCityAbbrev = departureCityAbbrev;
    }

    public void setDestinationCityName(String destinationCityName) {
        this.destinationCityName = destinationCityName;
    }

    public void setDestinationCityAbbrev(String destinationCityAbbrev) {
        this.destinationCityAbbrev = destinationCityAbbrev;
    }

    public void setCabinClass(String cabinClass) {
        this.cabinClass = cabinClass;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public void setSeniors(int seniors) {
        this.seniors = seniors;
    }

    public void setMonth1(int month1) {
        this.month1 = month1;
    }

    public void setDay1(int day1) {
        this.day1 = day1;
    }

    public void setYear1(int year1) {
        this.year1 = year1;
    }

    public void setMonth2(int month2) {
        this.month2 = month2;
    }

    public void setDay2(int day2) {
        this.day2 = day2;
    }

    public void setYear2(int year2) {
        this.year2 = year2;
    }

    public FlightUrlResult(String departureCityName, String departureCityAbbrev, String destinationCityName, String destinationCityAbbrev, String cabinClass, int children, int adults, int seniors,String d1, String d2) {
        this.departureCityName = departureCityName;
        this.departureCityAbbrev = departureCityAbbrev;
        this.destinationCityName = destinationCityName;
        this.destinationCityAbbrev = destinationCityAbbrev;
        this.cabinClass = cabinClass;
        this.children = children;
        this.adults = adults;
        this.seniors = seniors;
        this.d1 = d1;
        this.d2 = d2;
        parseDate();
    }
    private void parseDate(){
        String[] yearMonthDay = d1.split("-");
        day1= setDateVariable(yearMonthDay[2]);
        month1 = setDateVariable(yearMonthDay[1]);
        year1 = setDateVariable(yearMonthDay[0]);
        String[] yearMonthDay2 = d2.split("-");
        day2= setDateVariable(yearMonthDay2[2]);
        month2 = setDateVariable(yearMonthDay2[1]);
        year2 = setDateVariable(yearMonthDay2[0]);
    }
    private int setDateVariable(String variable){
     return Integer.parseInt(variable);
    }

    public Site[] getSiteList() {
        return siteList;
    }
}
