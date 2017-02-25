package com.example.joyrasmussen.hw6_group34;

import android.os.AsyncTask;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by joyrasmussen on 2/25/17.
 */

public class AsyncJson extends AsyncTask<Void, Void, ArrayList<App>>{
    MainActivity mainActivity;
    String ITUNES_RSS = "https://itunes.apple.com/us/rss/toppaidapplications/limit=25/json";
    public AsyncJson(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    @Override
    protected void onPostExecute(ArrayList<App> apps) {
        super.onPostExecute(apps);
        mainActivity.setAppArray(apps);
    }

    @Override
    protected ArrayList<App> doInBackground(Void... params) {
        StringBuilder result = new StringBuilder();
        URL url;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(ITUNES_RSS);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            InputStream in = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);

            int data = reader.read();

            while (data != -1) {

                char current = (char) data;
                result.append(current);

                data = reader.read();
            }



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
             return JsonParseHelper.parseApps(result.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
