package com.notifyme.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.notifyme.R;


import java.util.ArrayList;

/**
 * Created by sathya on 11/1/2016.
 */

public class GridViewAdapter extends BaseAdapter {
    private Context context;

    //Array List that would contain the urls and the titles for the images
    private ArrayList<Integer> images;
    private ArrayList<String> names;

    public GridViewAdapter(Context context, ArrayList<Integer> images, ArrayList<String> names) {
        //Getting all the values
        this.context = context;
        this.images = images;
        this.names = names;
    }

    @Override
    public int getCount() {

        return images.size();
    }

    @Override
    public Object getItem(int position) {

        return images.get(position);
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Holder holder = new Holder();
        View rowView;
        rowView = new View(context);
        rowView = inflater.inflate(R.layout.gridload, null);
        holder.tv = (TextView) rowView.findViewById(R.id.grid_text);
        holder.img = (ImageView) rowView.findViewById(R.id.grid_image);
        Glide.with(context).load(images.get(position)).into(holder.img);
        holder.tv.setText(names.get(position));

        return rowView;
    }

    public class Holder {
        TextView tv;
        ImageView img;


    }
}
