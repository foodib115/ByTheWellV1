package com.example.kevinc1.bythewellv1.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import com.example.kevinc1.bythewellv1.R;
import com.example.kevinc1.bythewellv1.model.Location;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {

    private List<Location> locations;
    private int rowLayout;
    private Context context;


    public LocationAdapter(List<Location> locations, int rowLayout, Context context) {
        this.locations = locations;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public LocationAdapter.LocationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new LocationViewHolder(view);
    }


    @Override
    public void onBindViewHolder(LocationViewHolder holder, final int position) {
        holder.positionNumber.setText("Location number : " + String.valueOf(position + 1));
        holder.id.setText("ID :" + locations.get(position).getId());
        holder.name.setText("Name: " + locations.get(position).getName());
        holder.distance.setText("Distance: " + locations.get(position).getDistance());
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    public static class LocationViewHolder extends RecyclerView.ViewHolder {
        TextView positionNumber;
        TextView id;
        TextView name;
        TextView distance;

        public LocationViewHolder(View v) {
            super(v);
            positionNumber = (TextView) v.findViewById(R.id.positionNumber);
            id = (TextView) v.findViewById(R.id.id);
            name = (TextView) v.findViewById(R.id.name);
            distance = (TextView) v.findViewById(R.id.distance);
        }
    }
}

