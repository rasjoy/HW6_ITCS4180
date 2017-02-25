package com.example.joyrasmussen.hw6_group34;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class FavoritesActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    ArrayList<App> favApps;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        ListView listView = (ListView) findViewById(R.id.favsListView);

        sharedPreferences = this.getSharedPreferences("com.example.joyrasmussen.hw6_group34", Context.MODE_PRIVATE);

        gson = new Gson();
        String json = sharedPreferences.getString("favs", "");

        if(!json.isEmpty()) {
            Type type = new TypeToken<ArrayList<App>>() {
            }.getType();
            favApps = gson.fromJson(json, type);

            final AppAdapter adapter = new AppAdapter(this, R.layout.app_layout, favApps);
            listView.setAdapter(adapter);
            adapter.setNotifyOnChange(true);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    gson = new Gson();
                    String json = sharedPreferences.getString("favs", "");
                    Type type = new TypeToken<ArrayList<App>>() {
                    }.getType();
                    favApps = gson.fromJson(json, type);

                    adapter.notifyDataSetChanged();

                }
            });


        }


    }

    public void finish(View view){

        finish();
    }
}
