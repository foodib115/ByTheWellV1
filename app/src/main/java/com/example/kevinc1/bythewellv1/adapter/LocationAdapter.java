package com.example.kevinc1.bythewellv1.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import com.example.kevinc1.bythewellv1.R;
import com.example.kevinc1.bythewellv1.activity.ProfileActivity;
import com.example.kevinc1.bythewellv1.model.Location;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {

    private static final String TAG = "Location Adapter";
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

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + locations.get(position).getId());

                //Toast.makeText(customers.get(position), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, ProfileActivity.class);
                intent.putExtra("ID", locations.get(position).getId().toString());
                intent.putExtra("Name", locations.get(position).getName());
                intent.putExtra("Distance", locations.get(position).getDistance());
                intent.putExtra("Latitude", locations.get(position).getLatitude());
                intent.putExtra("Longitude", locations.get(position).getLongitude());
                context.startActivity(intent);

            }
        });

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
        LinearLayout parentLayout;


        public LocationViewHolder(View v) {
            super(v);
            positionNumber = (TextView) v.findViewById(R.id.positionNumber);
            id = (TextView) v.findViewById(R.id.id);
            name = (TextView) v.findViewById(R.id.name);
            distance = (TextView) v.findViewById(R.id.distance);
            parentLayout = v.findViewById(R.id.parentloc);
        }
    }
}

