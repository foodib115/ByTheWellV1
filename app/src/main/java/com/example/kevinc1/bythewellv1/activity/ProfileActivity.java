package com.example.kevinc1.bythewellv1.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kevinc1.bythewellv1.R;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity{

    private static final String TAG = "ProfileActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Log.d(TAG, "onCreate: started.");

        getIncomingIntent();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");

        if(getIntent().hasExtra("ID") && getIntent().hasExtra("Name") && getIntent().hasExtra("Distance") && getIntent().hasExtra("Latitude") && getIntent().hasExtra("Longitude")){
            Log.d(TAG, "getIncomingIntent: found intent extras.");

            String id = getIntent().getStringExtra("ID");
            String name = getIntent().getStringExtra("Name");
            String distance = getIntent().getStringExtra("Distance");
            String latitude = getIntent().getStringExtra("Latitude");
            String longitude = getIntent().getStringExtra("Longitude");

            setProfile(id, name, distance, latitude, longitude);
        }
    }

    private void setProfile(String id, String name, String distance, String latitude, String longitude){
        Log.d(TAG, "setImage: setting the customer and orders to widgets.");

        TextView prof_id = findViewById(R.id.prof_id);
        prof_id.setText("ID: " + id);

        TextView prof_name = findViewById(R.id.prof_name);
        prof_name.setText("Name: " + name);

        TextView prof_distance = findViewById(R.id.prof_distance);
        prof_distance.setText("Distance: " + distance);

        TextView prof_latitude = findViewById(R.id.prof_latitude);
        prof_latitude.setText("Lat: " + latitude);

        TextView prof_longitude = findViewById(R.id.prof_longitude);
        prof_longitude.setText("Long: " + longitude);

        ImageView prof_mapimage = findViewById(R.id.prof_mapimage);
        Picasso.get().load("http://maps.google.com/maps/api/staticmap?center=" + latitude + "," + longitude + "&zoom=14&size=1200x650&maptype=roadmap&format=png&visual_refresh=true&markers=size:small%7Ccolor:0xff0000%7Clabel:1%7C"+latitude+","+longitude).into(prof_mapimage);

    }

}
