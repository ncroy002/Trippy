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

        Request request = new Request.Builder()
                .url("https://api.geoapify.com/v2/places?categories=" + interest +
                        "&filter=circle:-81.3790304,28.5421109,50000&bias=proximity:-81.3790304,28.5421109&limit=12&apiKey=e74299088e09495bb2120458d0ac22e2")
                //.url("https://api.geoapify.com/v2/places?categories=" + interest +
                //        "&filter=geometry:a93efddbfe77d592b8c42b376494bf24&bias=proximity:-81.3790304,28.5421109&lang=en&limit=12&apiKey=e74299088e09495bb2120458d0ac22e2")
                .get()
                .build();

        Response response = client.newCall(request).execute();
        String jsonData = response.body().string();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(jsonData);
        return(jsonData);
    }

}
