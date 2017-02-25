package com.example.joyrasmussen.hw6_group34;

import android.content.Context;
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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<App> appArray;
    SharedPreferences sharedPreferences;
    ProgressBar progress;
    TextView loading;
    ListView appList;
    AppAdapter appAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

                return true;
            case R.id.favorites:

                return true;
            case R.id.increase:

                return true;
            case R.id.decreasingly:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setAppArray(ArrayList<App> array){
        progress.setVisibility(View.INVISIBLE);
        loading.setVisibility(View.INVISIBLE);

        appArray = array;

        appAdapter = new AppAdapter(this, R.layout.app_layout, appArray);
        appAdapter.setNotifyOnChange(true);
        appList.setAdapter(appAdapter);



    }
}
