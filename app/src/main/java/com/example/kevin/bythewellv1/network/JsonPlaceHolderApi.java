package com.example.kevin.bythewellv1.network;

import com.example.kevin.bythewellv1.model.Locations;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {

        @GET("/getlocats")
        public Call<List<Locations>> getLocats();


}
