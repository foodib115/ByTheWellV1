package com.example.kevinc1.bythewellv1.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;
import android.util.Log;
import android.widget.Toast;

import com.example.kevinc1.bythewellv1.R;
import com.example.kevinc1.bythewellv1.adapter.LocationAdapter;
import com.example.kevinc1.bythewellv1.model.Location;
import com.example.kevinc1.bythewellv1.rest.RestClient;
import com.example.kevinc1.bythewellv1.rest.LocationAPIService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    LocationAPIService apiService;
    SearchView searchView;
    RecyclerView recyclerView;
    LocationAdapter adapter;
    List<Location> locations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView=(SearchView) findViewById(R.id.searchView);
        searchView.setQueryHint("Search View");

        apiService = RestClient.getClient().create(LocationAPIService.class);

        recyclerView = (RecyclerView) findViewById(R.id.LocationListRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new LocationAdapter(locations, R.layout.location_item, getApplicationContext());
        recyclerView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getBaseContext(), query, Toast.LENGTH_LONG).show();
                locations.clear();
                fetchShipmentList(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(getBaseContext(), newText, Toast.LENGTH_LONG).show();
                locations.clear();
                fetchShipmentList(newText);
                return false;
            }
        });





    }

    private void fetchShipmentList(String query) {
        Call<List<Location>> call = apiService.fetchLocations(query, "200");
        call.enqueue(new Callback<List<Location>>() {
            @Override
            public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {
                Log.d(TAG, "Total number of questions fetched : " + response.body().size());

                locations.addAll(response.body());
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<List<Location>> call, Throwable t) {
                Log.e(TAG, "Got error : " + t.getLocalizedMessage());
            }
        });
    }


}
