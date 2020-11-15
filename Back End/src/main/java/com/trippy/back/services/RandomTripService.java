package com.trippy.back.services;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.trippy.back.entities.Hotels;
import net.minidev.json.parser.ParseException;
import org.springframework.stereotype.Service;
import java.io.IOException;


@Service
public class RandomTripService {
    //variables for the call
    //index between 0 and 25
    String[] cityList = {"Orlando","Chicago","Houston","Phoenix" ,
                         "Philadelphia","Dallas","Denver","Austin",
                         "Charlotte","Seattle","Denver","Boston","Detroit",
                         "Nashville","Portland" ,"Milwaukee","Sacramento","Atlanta",
                         "Miami" ,"Honolulu","Newark","Madison","Reno" ,
                         "Gainesville","Columbia","LA"};
    //variables for hotel
    String hotelName;
    String starRating;
    String price;
    String address;
    String photo;

    OkHttpClient client = new OkHttpClient();


    public String generateSearchID(String cityName) throws IOException{
        String url = "https://rapidapi.p.rapidapi.com/locations/search?query="+cityName+"&locale=en_US";

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
    public Object[] findTrip(int randomNumber) throws IOException{
        //first we need the destination ID
        String getid = generateSearchID(cityList[randomNumber]);
        //next we setup the URL for the proper call
        String url =  "https://rapidapi.p.rapidapi.com/properties/list?destinationId="+getid+"&pageNumber=1&checkIn=2020-11-20&checkOut=2020-11-30&pageSize=25&adults1=1&currency=USD&locale=en_US&sortOrder=PRICE";
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
        Object[] tripInfo = new Object[3];
        hotelName = jsonNode.get("data").get("body").get("searchResults").get("results").get(0).get("name").toString();
        starRating = jsonNode.get("data").get("body").get("searchResults").get("results").get(0).get("starRating").toString();
        price = jsonNode.get("data").get("body").get("searchResults").get("results").get(0).get("ratePlan").get("price").get("current").toString();
        address = jsonNode.get("data").get("body").get("searchResults").get("results").get(0).get("address").get("streetAddress").toString();
        photo = jsonNode.get("data").get("body").get("searchResults").get("results").get(0).get("thumbnailUrl").toString();
        photo.replace("\"", "");

        tripInfo[0] = new Hotels(hotelName,starRating,address,price,photo);
        try {
            tripInfo[1] = browseRoutes(randomNumber);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("was this outputted properly");
        System.out.println(tripInfo[1]);
        tripInfo[2] = cityList[randomNumber];

        return  tripInfo;
    }

    public String[] browseRoutes(int randomNumber) throws IOException, ParseException {
        System.out.println("destination is");
        System.out.println(cityList[randomNumber]);

        OkHttpClient client = new OkHttpClient();
        String airport = getAirports(randomNumber);
        String reformatAirport = airport.replaceAll("\"", "");

        System.out.println("destination airport is");
        System.out.println(reformatAirport);

        String url = "https://rapidapi.p.rapidapi.com/apiservices/browseroutes/v1.0/US/USD/en-US/NYCA-sky/"+reformatAirport+"/2020-11-20?inboundpartialdate=2020-11-30";

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "1b25331decmsh2220286ae9fedcdp1e87f4jsn270397c114a4")
                .build();

        Response response = client.newCall(request).execute();


        //my stuff
        String jsonData = response.body().string();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(jsonData);
        System.out.println("PRINTING OUT BROWSE USING JSON NODE");
        System.out.println(jsonNode.toPrettyString());

        System.out.println(jsonNode.get("Quotes").get(0).get("MinPrice").toString());
        System.out.println(jsonNode.get("Carriers").get(0).get("Name").toString());
        System.out.println(jsonNode.get("Quotes").get(0).get("OutboundLeg").get("DepartureDate").toString());

        String[] flightInfos = new String[]{jsonNode.get("Quotes").get(0).get("MinPrice").toString(),jsonNode.get("Carriers").get(0).get("Name").toString(),jsonNode.get("Quotes").get(0).get("OutboundLeg").get("DepartureDate").toString()};

        /*
        JSONParser parser = new JSONParser(JSONParser.MODE_JSON_SIMPLE);
        JSONObject json = (JSONObject)parser.parse(response.body().string());
        JSONArray quotes = (JSONArray)json.get("Quotes");
        quotes.forEach(q -> {
            double minPrice = Double.parseDouble(((JSONObject) q).get("MinPrice").toString());
            ((JSONObject) q).put("MinPrice", minPrice);
            ((JSONObject) q).put("travelers", 1);
        });
        System.out.println("Looking at the return for browse");
        System.out.println(json.toString());

         */
        return flightInfos;
    }

    public String getAirports(int random) throws IOException {

        OkHttpClient client = new OkHttpClient();

        Request requestTo = new Request.Builder()
                .url("https://rapidapi.p.rapidapi.com/apiservices/autosuggest/v1.0/US/USD/en-US/?query="+cityList[random])
                .get()
                .addHeader("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "1b25331decmsh2220286ae9fedcdp1e87f4jsn270397c114a4")
                .build();

        Request requestFrom = new Request.Builder()
                .url("https://rapidapi.p.rapidapi.com/apiservices/autosuggest/v1.0/US/USD/en-US/?query=OKC")
                .get()
                .addHeader("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "1b25331decmsh2220286ae9fedcdp1e87f4jsn270397c114a4")
                .build();

        Response responseTo= client.newCall(requestTo).execute();
        Response responseFrom = client.newCall(requestFrom).execute();

        String jsonData = responseTo.body().string();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(jsonData);

        System.out.println(jsonNode.get("Places").get(0).get("PlaceId").toString());

        return jsonNode.get("Places").get(0).get("PlaceId").toString();
    }

}
