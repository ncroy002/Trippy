package com.trippy.back.services;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.trippy.back.entities.Hotels;

import com.squareup.okhttp.ResponseBody;
import com.trippy.back.entities.Trip;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class HotelsService {
    //variables
    String hotelName;
    String starRating;
    String price;
    String address;
    String photo;

    OkHttpClient client = new OkHttpClient();

    public String generateSearchID(String destinationName) throws IOException{
        //This will need to be optimized so that it can handle cities that are 2 named (new york for example)
        //String url = "https://rapidapi.p.rapidapi.com/locations/search?query="+trip.getCity2()+"&locale=en_US";
        String url = "https://rapidapi.p.rapidapi.com/locations/search?query="+destinationName+"&locale=en_US";

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("x-rapidapi-host", "hotels4.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "6c2d40eab1mshe146aef860fc697p165fcejsne60d1372e4d2")
                .build();

        Response response = client.newCall(request).execute();
        // Code above should return the enter body. now we need the search id
        //this code bellow would return the search id

        String jsonData = response.body().string();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(jsonData);

        String id = jsonNode.get("suggestions").get(0).get("entities").get(0).get("destinationId").toString();
        String onlyNumber = id.replaceAll("\\D+","");
        return onlyNumber;
    }

    public Hotels[] findHotel(String destinationName,String date1, String date2) throws IOException{
        //first we need the destination ID
        String getid = generateSearchID(destinationName);
        //next we setup the URL for the proper call
        String url =  "https://rapidapi.p.rapidapi.com/properties/list?destinationId="+getid+"&pageNumber=1&checkIn="+date1+"&checkOut="+date2+"&pageSize=25&adults1=1&currency=USD&locale=en_US&sortOrder=PRICE";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("x-rapidapi-host", "hotels4.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "6c2d40eab1mshe146aef860fc697p165fcejsne60d1372e4d2")
                .build();

        Response response = client.newCall(request).execute();
        String jsonData = response.body().string();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(jsonData);
        //now that we have the body lets map those info out
        Hotels[] hotelList = new Hotels[11];

        for(int i = 0; i <jsonNode.get("data").get("body").get("searchResults").get("results").size(); i++){

            if(i>10){
                break;
            }
            hotelName = jsonNode.get("data").get("body").get("searchResults").get("results").get(i).get("name").toString();
            starRating = jsonNode.get("data").get("body").get("searchResults").get("results").get(i).get("starRating").toString();
            price = jsonNode.get("data").get("body").get("searchResults").get("results").get(i).get("ratePlan").get("price").get("current").toString();
            address = jsonNode.get("data").get("body").get("searchResults").get("results").get(i).get("address").get("streetAddress").toString();
            photo = jsonNode.get("data").get("body").get("searchResults").get("results").get(i).get("thumbnailUrl").toString();
            photo.replace("\"", "");
            hotelList[i] = new Hotels(hotelName,starRating,address,price,photo);

        }
        return hotelList;
    }


}
