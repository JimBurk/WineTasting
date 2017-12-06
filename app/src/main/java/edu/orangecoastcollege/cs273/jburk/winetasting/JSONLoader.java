package edu.orangecoastcollege.cs273.jburk.winetasting;

/**
 * Created by pjsda on 12/2/2017.
 */

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Class loads Country data from a formatted JSON (JavaScript Object Notation) file.
 * Populates data model (Country) with data.
 */
public class JSONLoader {

    /**
     * Loads JSON data from a file in the assets directory.
     *
     * @param context The activity from which the data is loaded.
     * @throws IOException If there is an error reading from the JSON file.
     */
    public static List<AVA> loadJSONFromAsset(Context context) throws IOException {
        List<AVA> allAVAsList = new ArrayList<>();
        String json = null;
        InputStream is = context.getAssets().open("ava_list_JSON.json");
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        json = new String(buffer, "UTF-8");


        // Loop through all the countries in the JSON data, create a Country
        // object for each and add the object to the allCountriesList

        try {
            JSONObject jsonRootObject = new JSONObject(json);
            JSONArray allAVAsJSON = jsonRootObject.getJSONArray("ava_list_JSON");

            int length = allAVAsJSON.length();
            for (int i = 0; i < length; ++i)
            {
                JSONObject avaObject = allAVAsJSON.getJSONObject(i);
                String name = avaObject.getString("Name");
                String state = avaObject.getString("state");
                String county = avaObject.getString("County");
                String located = avaObject.getString("Located");
                String contains = avaObject.getString("Contains");
                AVA newAVA = new AVA(name, state, county, located, contains);
                allAVAsList.add(newAVA);
            }

        } catch (JSONException e) {
            Log.e("Flag Quiz", e.getMessage());
        }

        return allAVAsList;
    }
}
