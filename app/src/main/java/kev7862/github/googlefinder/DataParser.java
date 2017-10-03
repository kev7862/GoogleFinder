package kev7862.github.googlefinder;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by matite on 10/2/17.
 */

// This is where we fetch our Json Data
public class DataParser {
// first method
    private HashMap<String, String> getPlace(JSONObject googlePlaceJson) {
        HashMap<String, String> googlePlacesMap = new HashMap<>();
// Created a String where we can store our parameters
        String placeName = "-NA-";
        String vicinity = "-NA-";
        String latitude = "";
        String longitude = "";
        String reference = "";

        //fetching data from the api
        try {
        if (!googlePlaceJson.isNull("name")) {

            placeName = googlePlaceJson.getString("name");
        }

        if (!googlePlaceJson.isNull("vicinity")){

            vicinity = googlePlaceJson.getString("vicinity");
        }

            latitude = googlePlaceJson.getJSONObject("geometry").getJSONObject("location").getString("lat");
            longitude = googlePlaceJson.getJSONObject("geometry").getJSONObject("location").getString("lng");

            reference = googlePlaceJson.getString("reference");

            // Placing all our data that we've fetched in the googlePlacesMap
            googlePlacesMap.put("place_name", placeName);
            googlePlacesMap.put("vicinity", vicinity);
            googlePlacesMap.put("lat", latitude);
            googlePlacesMap.put("lng", longitude);
            googlePlacesMap.put("reference", reference);


            } catch (JSONException e) {
                e.printStackTrace();
            }
            return googlePlacesMap;
        }

        // This 2nd method is going to return a list of HashMaps.
    private List<HashMap<String, String>> getPlaces(JSONArray jsonArray) {
        int count = jsonArray.length();
        List<HashMap<String, String>> placesList = new ArrayList<>();
        HashMap<String, String> placeMap = null;

        for(int i = 0;i<count;i++) {
// We're using the get() method to fetch one place, and then add it to the List of HashMaps
            try {
                placeMap = getPlace((JSONObject) jsonArray.get(i));
                placesList.add(placeMap);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return placesList;
    }

    // Method 3: This will Parse the Json data and send it to get places method, take the Json Array then the
    //get places method will call the get place method using the for loop and it will fetch each element from the
    //JsonArray for each place and then store it in the places List.
    public List<HashMap<String, String>> parse(String jsonData) {
        JSONArray jsonArray = null;
        JSONObject jsonObject;

        try {
            jsonObject = new JSONObject(jsonData);
            jsonArray = jsonObject.getJSONArray("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return getPlaces(jsonArray);
    }
    }
