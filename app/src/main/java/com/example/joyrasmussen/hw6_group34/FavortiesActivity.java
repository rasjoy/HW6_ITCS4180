package com.example.joyrasmussen.hw6_group34;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FavortiesActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    Set<App> favApps;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorties);

        ListView listView = (ListView) findViewById(R.id.favListView);

        sharedPreferences = this.getSharedPreferences("com.example.joyrasmussen.hw6_group34", Context.MODE_PRIVATE);

        gson = new Gson();
        String json = sharedPreferences.getString("favs", "");

        if(json.isEmpty()){
            //No favorites
        } else {
            Type type = new TypeToken<List<App>>() {
            }.getType();
            favApps = gson.fromJson(json, type);
        }






    }
}
