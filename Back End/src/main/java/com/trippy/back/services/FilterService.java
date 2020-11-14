package com.trippy.back.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import com.trippy.back.BackApplication;
import net.minidev.json.JSONObject;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FilterService {
    OkHttpClient client = new OkHttpClient();

    public String generateInterestResults(String destination, String interest) throws IOException {

        Request location = new Request.Builder()
            .url("https://api.geoapify.com/v1/geocode/search?text=" + destination + "&apiKey=e74299088e09495bb2120458d0ac22e2")
                .get()
                .build();

        Response response = client.newCall(location).execute();
        String jsonData = response.body().string();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(jsonData);

        String lon = jsonNode.get("features").get(0).get("properties").get("lon").toString();
        String lat = jsonNode.get("features").get(0).get("properties").get("lat").toString();


        Request request = new Request.Builder()
                .url("https://api.geoapify.com/v2/places?categories=" + interest +
                        "&filter=circle:" + lon + "," + lat + ",50000&lang=en&limit=12&apiKey=e74299088e09495bb2120458d0ac22e2")
                .get()
                .build();

        Response response2 = client.newCall(request).execute();
        String jsonData2 = response2.body().string();
        return(jsonData2);
    }

}
