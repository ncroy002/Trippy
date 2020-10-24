package com.trippy.back.services;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.trippy.back.entities.FlightUrlResult;
import com.trippy.back.entities.Trip;
import com.trippy.back.enumeration.Site;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



@Service
public class FlightService {
    OkHttpClient client = new OkHttpClient();


    public String generateSearchID(Trip trip) throws IOException {
        String url;
        if(trip.getFlight().getFlightType() ==2){
            url = "https://rapidapi.p.rapidapi.com/GetPricesAPI/StartFlightSearch.aspx?" +
                    "lapinfant="+ trip.getFlight().getLapInfant() /*.ordinal()*/ +"&child="+ trip.getFlight().getChild()/*.ordinal()*/ +
                    "&city2="+trip.getCity2()+"&date1="+trip.getDate1()+"&youth="+trip.getFlight().getYouth()/*.toString()*/+
                    "&flightType="+trip.getFlight().getFlightType()+"&adults="+trip.getFlight().getAdults()/*.toString()*/+"&cabin="+
                    String.valueOf(trip.getFlight().getCabin()) +"&infant="+trip.getFlight().getInfant()/*.ordinal()*/+"&city1="+trip.getCity1()+"&seniors="+
                    trip.getFlight().getSeniors()/*.ordinal()*/ +"&date2="+trip.getDate2() +"&islive=true";
        }
        else {
           url = "https://rapidapi.p.rapidapi.com/GetPricesAPI/StartFlightSearch.aspx?" +
                    "lapinfant="+ trip.getFlight().getLapInfant() /*.ordinal()*/ +"&child="+ trip.getFlight().getChild()/*.ordinal()*/ +
                    "&city2="+trip.getDate2()+"&date1="+trip.getDate1()+"&youth="+trip.getFlight().getYouth()/*.toString()*/+
                    "&flightType="+trip.getFlight().getFlightType()+"&adults="+trip.getFlight().getAdults()/*.toString()*/+"&cabin="+
                    String.valueOf(trip.getFlight().getCabin()) +"&infant="+trip.getFlight().getInfant()/*.ordinal()*/+"&city1="+trip.getCity1()+"&seniors="+
                    trip.getFlight().getSeniors()/*.ordinal()*/ +"&islive=true";
        }


        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("x-rapidapi-host", "compare-flight-prices.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "1b25331decmsh2220286ae9fedcdp1e87f4jsn270397c114a4")
                .build();

        Response response = client.newCall(request).execute();
        //This is the search id

      return cleanIdResponse(response.body().string());
    }

    private String cleanIdResponse(String json){
        json = json.substring(13);
        int ending =  json.indexOf("}") -1;
        json = json.substring(0,ending);
        return json;
    }

    public List<FlightUrlResult> searchResults(String flightSearchID) throws IOException, ParseException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<FlightUrlResult> flightUrlResults = new ArrayList<>();
        Request request = new Request.Builder()
                .url("https://compare-flight-prices.p.rapidapi.com/GetPricesAPI/GetPrices.aspx?SearchID="+ flightSearchID)
                .get()
                .addHeader("x-rapidapi-host", "compare-flight-prices.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "1b25331decmsh2220286ae9fedcdp1e87f4jsn270397c114a4")
                .build();
        Response response = client.newCall(request).execute();
        //map json response body to list of Flight Price Results
        JsonNode rootNode = objectMapper.readTree(response.body().string());
        FlightUrlResult urlResult = new FlightUrlResult();
        for(int i = 0; i < rootNode.size(); i++){
            urlResult = new FlightUrlResult();
            urlResult.setSite(rootNode.get(i).get("site").toString());
            urlResult.setUrl(rootNode.get(i).get("url").toString());
            flightUrlResults.add(urlResult);
        }
        return flightUrlResults;
    }

    public String generateUrl(FlightUrlResult flightUrlResult){
        //creates url
        flightUrlResult.setUrl(flightUrlResult.getUrl());
        return flightUrlResult.getUrl();
    }

    public String determineSite(Site site){
        String website = null;
        switch(site) {
            case EXPEDIA:
                website = "www.expedia.com";
                break;
            case TRAVELOCITY:
                website = "www.travelocity.com";
                break;
            case KAYAK:
                website = "www.kayak.com";
                break;
        }
        return website;
    }

    public String browseRoutes(Trip trip) throws IOException{
        OkHttpClient client = new OkHttpClient();
        String url = null;
        if(trip.getDate2() == null){
           url = "https://rapidapi.p.rapidapi.com/apiservices/browseroutes/v1.0/US/USD/en-US/"+trip.getCity1()+"/"+trip.getCity2()+"/"+trip.getDate1();
        }
        else{
            url = "https://rapidapi.p.rapidapi.com/apiservices/browseroutes/v1.0/US/USD/en-US/"+trip.getCity1()+"/"+trip.getCity2()+"/"+trip.getDate1()+"?inboundpartialdate="+trip.getDate2();
        }
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "1b25331decmsh2220286ae9fedcdp1e87f4jsn270397c114a4")
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public JSONObject getAirports(Trip trip) throws IOException {

        JSONObject returnObject = new JSONObject();

        OkHttpClient client = new OkHttpClient();

        Request requestTo = new Request.Builder()
                .url("https://rapidapi.p.rapidapi.com/apiservices/autosuggest/v1.0/"+ trip.getCountry()+"/GBP/en-GB/?query="+trip.getCity2())
                .get()
                .addHeader("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "1b25331decmsh2220286ae9fedcdp1e87f4jsn270397c114a4")
                .build();

        Request requestFrom = new Request.Builder()
                .url("https://rapidapi.p.rapidapi.com/apiservices/autosuggest/v1.0/"+ trip.getCountry()+"/GBP/en-GB/?query="+trip.getCity1())
                .get()
                .addHeader("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "1b25331decmsh2220286ae9fedcdp1e87f4jsn270397c114a4")
                .build();

        Response responseTo= client.newCall(requestTo).execute();
        Response responseFrom = client.newCall(requestFrom).execute();

        
        returnObject.put("city1", responseFrom.body().string());
        returnObject.put("city2", responseTo.body().string());


        return returnObject;
    }

}
