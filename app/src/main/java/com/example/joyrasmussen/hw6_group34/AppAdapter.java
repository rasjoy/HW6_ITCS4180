package com.example.joyrasmussen.hw6_group34;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by joyrasmussen on 2/25/17.
 */

public class AppAdapter extends ArrayAdapter<App> {
    Context mContext;
    List<App> data;
    int mresource;

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
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mresource, parent, false);
            holder  = new ViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.image);
            holder.appText = (TextView) convertView.findViewById(R.id.appText);
            holder.favButton = (ImageButton) convertView.findViewById(R.id.favButton);
            convertView.setTag(holder);

        }

        App app = data.get(position);


        holder = (ViewHolder) convertView.getTag();
        TextView text = holder.appText;
        ImageView image = holder.image;
        ImageButton fav = holder.favButton;
        if(app.isFavorite){
            fav.setBackgroundResource(R.drawable.black_star);
        }
        text.setText(app.toString());

        if(app.getImageURL() != null){
            Picasso.with(convertView.getContext()).load(app.getImageURL()).into(image);
        }


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

