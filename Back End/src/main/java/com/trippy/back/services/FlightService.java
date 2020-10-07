package com.trippy.back.services;


import com.fasterxml.jackson.databind.ObjectMapper;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import com.trippy.back.entities.Flight;
import net.minidev.json.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FlightService {
    OkHttpClient client = new OkHttpClient();

    public String generateSearchID(Flight flight) throws IOException {
        String url;
        if(flight.getFlightType() ==1){
            url = "https://rapidapi.p.rapidapi.com/GetPricesAPI/StartFlightSearch.aspx?" +
                    "lapinfant="+ flight.getLapInfant() /*.ordinal()*/ +"&child="+ flight.getChild()/*.ordinal()*/ +
                    "&city2=NYC&date1=2020-10-29"+/*flight.dateToString(flight.getDate1())+*/"&youth="+flight.getYouth()/*.toString()*/+
                    "&flightType="+flight.getFlightType()+"&adults="+flight.getAdults()/*.toString()*/+"&cabin="+
                    String.valueOf(flight.getCabin()) +"&infant="+flight.getInfant()/*.ordinal()*/+"&city1=LAX&seniors="+
                    flight.getSeniors()/*.ordinal()*/ +"&date2=2021-01-02" +"&islive=true";
        }
        else {
           url = "https://rapidapi.p.rapidapi.com/GetPricesAPI/StartFlightSearch.aspx?" +
                    flight.dateToString(flight.getDate2())+"&lapinfant="+ flight.getLapInfant() /*.ordinal()*/ +"&child="+ flight.getChild()/*.ordinal()*/ +
                    "&city2=NYC&date1="+flight.dateToString(flight.getDate1())+"&youth="+flight.getYouth()/*.toString()*/+
                    "&flightType="+flight.getFlightType()+"&adults="+flight.getAdults()/*.toString()*/+"&cabin="+
                    String.valueOf(flight.getCabin()) +"&infant="+flight.getInfant()/*.ordinal()*/+"&city1=LAX&seniors="+
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

}
