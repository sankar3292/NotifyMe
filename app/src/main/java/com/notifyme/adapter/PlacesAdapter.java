package com.notifyme.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.notifyme.R;
import com.notifyme.model.Result;

import java.util.List;

/**
 * Created by netvation on 4/26/2017.
 */

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.ViewHolder>
{
    private List<Result> placesModelList;
    private Context context;
    public PlacesAdapter(List<Result> placesModels, Context context)
    {
        this.placesModelList=placesModels;
        this.context=context;
    }
    @Override
    public PlacesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.places_inflate_row,parent, false);
        PlacesAdapter.ViewHolder viewHolder = new PlacesAdapter.ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PlacesAdapter.ViewHolder holder, int position)
    {
       Result placesModelobj=placesModelList.get(position);
        holder.places_name.setText(placesModelobj.getName());
        holder.places_address.setText(placesModelobj.getVicinity());
    }

    @Override
    public int getItemCount() {
        return placesModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView places_name,places_type,places_address;
        private ImageView navigatetoplaces,call;
        public ViewHolder(View itemView)
        {
            super(itemView);
            places_name= (TextView) itemView.findViewById(R.id.places_name);
            places_type= (TextView) itemView.findViewById(R.id.places_type);
            places_address= (TextView) itemView.findViewById(R.id.place_address);
            navigatetoplaces= (ImageView) itemView.findViewById(R.id.places_navigation);
            call= (ImageView) itemView.findViewById(R.id.places_call);
        }
    }
}
