package com.example.joyrasmussen.hw6_group34;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    ArrayList<App> appArray;
    SharedPreferences sharedPreferences;
    ProgressBar progress;
    TextView loading;
    ListView appList;
    AppAdapter appAdapter;
    Boolean dataRetrieved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataRetrieved = false;
        sharedPreferences = this.getSharedPreferences("com.example.joyrasmussen.hw6_group34", Context.MODE_PRIVATE);
        progress = (ProgressBar) findViewById(R.id.progress);
        loading = (TextView) findViewById(R.id.loadText);
        appList = (ListView) findViewById(R.id.appList);

        new AsyncJson(this).execute();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.refresh:
                refresh();
                return true;
            case R.id.favorites:
                Intent i = new Intent(getApplicationContext(), FavoritesActivity.class);
                startActivity(i);


                return true;
            case R.id.increase:
                sortIncreasing();

                return true;
            case R.id.decreasingly:
                sortDecreasing();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setAppArray(ArrayList<App> array){
        progress.setVisibility(View.INVISIBLE);
        loading.setVisibility(View.INVISIBLE);
        appList.setVisibility(View.VISIBLE);

        appArray = array;

        appAdapter = new AppAdapter(this, R.layout.app_layout, appArray);
        appAdapter.setNotifyOnChange(true);
        appList.setAdapter(appAdapter);

        dataRetrieved = true;

    }
    public void refresh(){
        appList.setVisibility(View.INVISIBLE);
        new AsyncJson(this).execute();
        progress.setVisibility(View.VISIBLE);
        loading.setVisibility(View.VISIBLE);



    }

    public void sortIncreasing(){

        if(appArray != null) {

            Collections.sort(appArray, new Comparator<App>() {
                @Override
                public int compare(App a1, App a2) {

                    float price1 = Float.parseFloat(a1.getPrice().substring(1));
                    float price2 = Float.parseFloat(a2.getPrice().substring(1));

                    return Math.round(price1 - price2);
                }
            });

            appAdapter.notifyDataSetChanged();

        }

    }

    public void sortDecreasing(){

        if(appArray != null) {
            Collections.sort(appArray, new Comparator<App>() {
                @Override
                public int compare(App a1, App a2) {

                    float price1 = Float.parseFloat(a1.getPrice().substring(1));
                    float price2 = Float.parseFloat(a2.getPrice().substring(1));

                    return Math.round(price2 - price1);
                }
            });

            appAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(dataRetrieved) {

            sharedPreferences = this.getSharedPreferences("com.example.joyrasmussen.hw6_group34", Context.MODE_PRIVATE);
            Gson gson = new Gson();
            String json = sharedPreferences.getString("favs", "");

            if (!json.isEmpty()) {
                Type type = new TypeToken<ArrayList<App>>() {
                }.getType();
                ArrayList<App> favApps = gson.fromJson(json, type);

                for (App a : appArray) { //This compares the favorite apps in SharedPrefs to the current apps on the page
                                         //if a current app is marked as a favorite, but it is not found in SharedPrefs then
                                        //it no longer is marked as a favorite. The adapter is then reloaded.
                    boolean notFoundInFavs = true;
                    boolean fav = false;

                    if(a.getFavorite() == true){
                        fav = true;
                    }

                    for (App favApp : favApps) {
                        if(a.getName().equals(favApp.getName())){
                            notFoundInFavs = false;
                        }
                    }

                    if(fav == true && notFoundInFavs == true){
                        a.setFavorite(false);
                    }
                }

                appAdapter = new AppAdapter(this, R.layout.app_layout, appArray);
                appAdapter.setNotifyOnChange(true);
                appList.setAdapter(appAdapter);
                appAdapter.notifyDataSetChanged();


            }
        }
    }
}
