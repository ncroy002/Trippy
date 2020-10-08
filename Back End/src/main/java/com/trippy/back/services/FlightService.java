package com.trippy.back.services;


import com.fasterxml.jackson.databind.ObjectMapper;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import com.trippy.back.entities.Airport;
import com.trippy.back.entities.Flight;
import com.trippy.back.entities.Trip;
import com.trippy.back.wrappers.ListPlaces;
import com.trippy.back.wrappers.Places;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlightService {
    OkHttpClient client = new OkHttpClient();


    public String generateSearchID(Flight flight) throws IOException {
        String url;
        if(flight.getFlightType() ==2){
            url = "https://rapidapi.p.rapidapi.com/GetPricesAPI/StartFlightSearch.aspx?" +
                    "lapinfant="+ flight.getLapInfant() /*.ordinal()*/ +"&child="+ flight.getChild()/*.ordinal()*/ +
                    "&city2="+flight.getCity2()+"&date1="+flight.getDate1()+"&youth="+flight.getYouth()/*.toString()*/+
                    "&flightType="+flight.getFlightType()+"&adults="+flight.getAdults()/*.toString()*/+"&cabin="+
                    String.valueOf(flight.getCabin()) +"&infant="+flight.getInfant()/*.ordinal()*/+"&city1="+flight.getCity1()+"&seniors="+
                    flight.getSeniors()/*.ordinal()*/ +"&date2="+flight.getDate2() +"&islive=true";
        }
        else {
           url = "https://rapidapi.p.rapidapi.com/GetPricesAPI/StartFlightSearch.aspx?" +
                    "lapinfant="+ flight.getLapInfant() /*.ordinal()*/ +"&child="+ flight.getChild()/*.ordinal()*/ +
                    "&city2="+flight.getDate2()+"&date1="+flight.getDate1()+"&youth="+flight.getYouth()/*.toString()*/+
                    "&flightType="+flight.getFlightType()+"&adults="+flight.getAdults()/*.toString()*/+"&cabin="+
                    String.valueOf(flight.getCabin()) +"&infant="+flight.getInfant()/*.ordinal()*/+"&city1="+flight.getCity1()+"&seniors="+
                    flight.getSeniors()/*.ordinal()*/ +"&islive=true";
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
    public ResponseBody searchResults(String flightSearchID) throws IOException, ParseException {
        ObjectMapper objectMapper = new ObjectMapper();
        flightSearchID = "ap-K3ishzWpPx8EQGua";
        Request request = new Request.Builder()
                .url("https://compare-flight-prices.p.rapidapi.com/GetPricesAPI/GetPrices.aspx?SearchID="+ flightSearchID)
                .get()
                .addHeader("x-rapidapi-host", "compare-flight-prices.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "1b25331decmsh2220286ae9fedcdp1e87f4jsn270397c114a4")
                .build();
//todo: could return response body
        Response response = client.newCall(request).execute();
        //map json response body to list of Flight Price Results


        //List<FlightPriceResult> resultList = objectMapper.readValue(response.body().string(), new TypeReference<List<FlightPriceResult>>(){});
        return  response.body();
    }
    public String browseRoutes(Flight flight) throws IOException{
        OkHttpClient client = new OkHttpClient();
        String url = null;
        if(flight.getDate2() == null){
           url = "https://rapidapi.p.rapidapi.com/apiservices/browseroutes/v1.0/US/USD/en-US/"+flight.getCity1()+"/"+flight.getCity2()+"/"+flight.getDate1();
        }
        else{
            url = "https://rapidapi.p.rapidapi.com/apiservices/browseroutes/v1.0/US/USD/en-US/"+flight.getCity1()+"/"+flight.getCity2()+"/"+flight.getDate1()+"inboundpartialdate="+flight.getDate2();
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
                .url("https://rapidapi.p.rapidapi.com/apiservices/autosuggest/v1.0/"+ trip.getCountry()+"/GBP/en-GB/?query="+trip.getFlight().getCity2())
                .get()
                .addHeader("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "1b25331decmsh2220286ae9fedcdp1e87f4jsn270397c114a4")
                .build();

        Request requestFrom = new Request.Builder()
                .url("https://rapidapi.p.rapidapi.com/apiservices/autosuggest/v1.0/"+ trip.getCountry()+"/GBP/en-GB/?query="+trip.getFlight().getCity1())
                .get()
                .addHeader("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "1b25331decmsh2220286ae9fedcdp1e87f4jsn270397c114a4")
                .build();

        Response responseTo= client.newCall(requestTo).execute();
        Response responseFrom = client.newCall(requestFrom).execute();

        String test = responseFrom.body().string();
        Gson gson = new Gson();
        ListPlaces listPlaces = gson.fromJson(test, ListPlaces.class);
        listPlaces.setPlaces(gson.fromJson(test, Places.class));
       // Airport[] airports = gson.fromJson(test, Airport[].class);
        //listPlaces.getPlaces().setAirports(airports);


          returnObject.put("city1", listPlaces);
//        returnObject.put("city2", responseTo.body().string());


        return returnObject;
        //List<Airport> airports = objectMapper.readValue(response.body().string(), new TypeReference<List<Airport>>() {});
    }

}
