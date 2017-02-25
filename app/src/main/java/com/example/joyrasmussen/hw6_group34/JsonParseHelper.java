package com.example.joyrasmussen.hw6_group34;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by joyrasmussen on 2/25/17.
 */

public class JsonParseHelper {

    public static ArrayList<App> parseApps(String in) throws JSONException {
        ArrayList<App> appList = new ArrayList<>();
        JSONObject root = new JSONObject(in);
        JSONArray appArray = root.getJSONObject("feed").getJSONArray("entry");
        for(int i = 0; i < appArray.length(); i++){
            JSONObject appJSON = appArray.getJSONObject(i);
            App app = new App();
            app.setName(appJSON.getJSONObject("title").getString("label"));
            JSONArray image = appJSON.getJSONArray("im:image");
            app.setImageURL(image.getJSONObject(0).getString("label"));
            app.setPrice(appJSON.getJSONObject("im:price").getString("label"));
            app.setCurrency(appJSON.getJSONObject("im:price").getJSONObject("attributes").getString("currency"));

            appList.add(app);

        }
        return appList;
    }
}
