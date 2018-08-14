package com.example.kevinc1.bythewellv1.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.example.kevinc1.bythewellv1.adapter.LocationAdapter;
import com.example.kevinc1.bythewellv1.model.Location;
import com.example.kevinc1.bythewellv1.R;
import com.example.kevinc1.bythewellv1.rest.LocationAPIService;
import com.example.kevinc1.bythewellv1.rest.RestClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
/*************************
    private static final String TAG = MainActivity.class.getSimpleName();

    LocationAPIService apiService;
    android.widget.SearchView searchView;
    RecyclerView recyclerView;
    LocationAdapter adapter;
    List<Location> locations = new ArrayList<>();

    @Override
    public boolean onCreateOptionsMenu( Menu menu) {

        setContentView(R.layout.activity_main);

        searchView=(android.widget.SearchView) findViewById(R.id.searchView);
        searchView.setQueryHint("Search View");

        apiService = RestClient.getClient().create(LocationAPIService.class);

        recyclerView = (RecyclerView) findViewById(R.id.LocationListRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new LocationAdapter(locations, R.layout.location_item, getApplicationContext());
        recyclerView.setAdapter(adapter);

        getMenuInflater().inflate( R.menu.options_menu, menu);

        MenuItem myActionMenuItem = menu.findItem( R.id.action_search);
        final android.widget.SearchView searchView = (android.widget.SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                locations.clear();
                fetchShipmentList(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });

        return true;
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
*****************************/
}
