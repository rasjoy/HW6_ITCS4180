package com.example.joyrasmussen.hw6_group34;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

/**
 * Created by joyrasmussen on 2/25/17.
 */

public class AppAdapter extends ArrayAdapter<App> {
    Context mContext;
    List<App> data;
    int mresource;
    SharedPreferences sharedPreferences;
    ArrayList<App> favApps;

    public AppAdapter(Context context, int resource, List<App> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.data = objects;
        this.mresource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        //if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mresource, parent, false);
            holder  = new ViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.image);
            holder.appText = (TextView) convertView.findViewById(R.id.appText);
            holder.favButton = (ImageButton) convertView.findViewById(R.id.favButton);
            convertView.setTag(holder);

       // }

        final App app = data.get(position);


        holder = (ViewHolder) convertView.getTag();
        TextView text = holder.appText;
        ImageView image = holder.image;
        final ImageButton fav = holder.favButton;

        text.setText(app.toString());

        if(app.getImageURL() != null){
            Picasso.with(convertView.getContext()).load(app.getImageURL()).into(image);
        }

        sharedPreferences = mContext.getSharedPreferences("com.example.joyrasmussen.hw6_group34", Context.MODE_PRIVATE);

        Gson gson = new Gson();
        String json = sharedPreferences.getString("favs", "");

        if(!json.isEmpty()){
            Type type = new TypeToken<ArrayList<App>>() {
            }.getType();
            favApps = gson.fromJson(json, type);

            for(App favApp : favApps){

                if (favApp.getName().equals(app.getName())){
                    if(favApp.getFavorite()){
                        app.setFavorite(true);
                    }
                }
            }
        } else {
            favApps = new ArrayList<App>();
        }

        if(app.isFavorite){
            fav.setImageResource(R.drawable.black_star);
        }

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    if (app.isFavorite) {

                        new AlertDialog.Builder(mContext).setTitle("Remove app?")
                                .setMessage("Remove this app from favorites?")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        fav.setImageResource(R.drawable.white_star);
                                        app.setFavorite(false);

                                        for (App a : favApps) {
                                            if (a.getName().equals(app.getName())) {
                                                favApps.remove(a);
                                            }
                                        }

                                        Gson gson = new Gson();
                                        String json = gson.toJson(favApps);

                                        sharedPreferences.edit().putString("favs", json).apply();
                                    }
                                })
                                .setNegativeButton("No", null)
                                .show();

                    } else {
                        fav.setImageResource(R.drawable.black_star);
                        app.setFavorite(true);
                        favApps.add(app);

                        Gson gson = new Gson();
                        String json = gson.toJson(favApps);

                        sharedPreferences.edit().putString("favs", json).apply();


                    }
                } catch (ConcurrentModificationException e){

                }
            }
        });






        return convertView;

        }
    static class ViewHolder{
        TextView appText;
        ImageButton favButton;
        ImageView image;


    }

    public void clearData() {
        // clear the data
        data.clear();
    }

}

