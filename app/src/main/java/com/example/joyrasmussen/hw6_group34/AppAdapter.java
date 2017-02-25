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

import java.util.List;

/**
 * Created by joyrasmussen on 2/25/17.
 */

public class AppAdapter extends ArrayAdapter<Apps> {
    Context mContext;
    List<Apps> data;
    int mresource;

    public AppAdapter(Context context, int resource, List<Apps> objects) {
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




        return convertView;

        }
    static class ViewHolder{
        TextView appText;
        ImageButton favButton;
        ImageView image;


    }
}

