package com.example.kevin.bythewellv1.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class NetworkService {
    private static NetworkService mInstance;
    private static final String BASE_URL = "http://bythewellapp.000webhostapp.com/task_manager2/v1/";
    private Retrofit mRetrofit;

    private NetworkService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkService getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkService();
        }
        return mInstance;
    }

    public JsonPlaceHolderApi getJSONApi() {
        return mRetrofit.create(JsonPlaceHolderApi.class);
    }
}
