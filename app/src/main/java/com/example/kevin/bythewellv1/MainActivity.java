package com.example.kevin.bythewellv1;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.kevin.bythewellv1.network.NetworkService;
import com.example.kevin.bythewellv1.model.Locations;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.POST;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NetworkService.getInstance()
                .getJSONApi()
                .getLocats()
                .enqueue(new Callback<List<Locations>>() {
                    TextView textView = (TextView) findViewById(R.id.textView);
                    @Override
                    public void onResponse(@NonNull Call<List<Locations>> call, @NonNull Response<List<Locations>> response) {
                        List<Locations> post = response.body();
                        if (post!=null) {
                            textView.append(post.get(1).getId() + "\n");
                            textView.append(post.get(1).getName() + "\n");
                            textView.append(post.get(1).getDistance() + "\n");
                        } else {
                            textView.append("Could not grab json data correctly");
                        }

                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Locations>> call, @NonNull Throwable t) {

                        textView.append("Error occurred while getting request!");
                        t.printStackTrace();
                    }
                });

    }
}
