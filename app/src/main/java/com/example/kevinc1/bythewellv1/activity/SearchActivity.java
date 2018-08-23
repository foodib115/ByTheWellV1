package com.example.kevinc1.bythewellv1.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

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

    private static final String TAG = SearchActivity.class.getSimpleName();

    LocationAPIService apiService;
    android.widget.SearchView searchView;
    RecyclerView recyclerView;
    LocationAdapter adapter;
    List<Location> locations = new ArrayList<>();
    String query;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);

        apiService = RestClient.getClient().create(LocationAPIService.class);

        recyclerView = (RecyclerView) findViewById(R.id.LocationListRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new LocationAdapter(locations, R.layout.location_item, getApplicationContext());
        recyclerView.setAdapter(adapter);

        Bundle extras=getIntent().getExtras();
        if(extras!=null) {
            query = extras.getString("query");
            fetchShipmentList(query);
        }

        dl = (DrawerLayout)findViewById(R.id.activity_search);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView)findViewById(R.id.nvsearch);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.home) {
                    Toast.makeText(SearchActivity.this, "Home",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                    startActivity(intent);
                }

                else if (id == R.id.profile) {
                    Toast.makeText(SearchActivity.this, "profile",Toast.LENGTH_SHORT).show();
                }

                else if (id == R.id.login) {
                    Toast.makeText(SearchActivity.this, "Login",Toast.LENGTH_SHORT).show();
                    Intent intentLogin = new Intent(SearchActivity.this, LoginActivity.class);
                    startActivity(intentLogin);
                }
                /******
                switch(id)
                {
                    case R.id.home:
                        Toast.makeText(SearchActivity.this, "Home",Toast.LENGTH_SHORT).show();
                        //Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                        //startActivity(intent);
                    case R.id.profile:
                        Toast.makeText(SearchActivity.this, "Profile",Toast.LENGTH_SHORT).show();
                    case R.id.login:
                        Toast.makeText(SearchActivity.this, "Login",Toast.LENGTH_SHORT).show();
                        //Intent intentLogin = new Intent(SearchActivity.this, LoginActivity.class);
                        //startActivity(intentLogin);
                    default:
                        return true;
                }
                *******/

                return true;

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu( Menu menu) {
        getMenuInflater().inflate( R.menu.options_menu, menu);

        MenuItem myActionMenuItem = menu.findItem( R.id.action_search);
        final SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

}
