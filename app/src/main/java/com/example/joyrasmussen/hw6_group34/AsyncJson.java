package com.example.joyrasmussen.hw6_group34;

import android.os.AsyncTask;

import java.util.ArrayList;

/**
 * Created by joyrasmussen on 2/25/17.
 */

public class AsyncJson extends AsyncTask<Void, Void, ArrayList<Apps>>{
    MainActivity mainActivity;
    String ITUNES_RSS = "https://itunes.apple.com/us/rss/toppaidapplications/limit=25/json";
    public AsyncJson(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }


    @Override
    protected ArrayList<Apps> doInBackground(Void... params) {


        return null;
    }
}
