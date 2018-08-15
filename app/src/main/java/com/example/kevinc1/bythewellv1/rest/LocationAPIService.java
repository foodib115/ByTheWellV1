package com.example.kevinc1.bythewellv1.rest;

import com.example.kevinc1.bythewellv1.model.Location;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LocationAPIService {

    @GET("/task_manager2/v1/getlocat")
    Call<List<Location>> fetchLocations(@Query("address") String shipName, @Query("distance") String distance);

}
