package com.example.bewkitsada.loginregistration2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.bewkitsada.loginregistration2.models.RequestInterface;
import com.example.bewkitsada.loginregistration2.models.ServerResponse;
import com.example.bewkitsada.loginregistration2.models.machineprocess.MachineAdapter;
import com.example.bewkitsada.loginregistration2.models.machineprocess.MachineName;
import com.example.bewkitsada.loginregistration2.string.Constants;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MachineActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<MachineName> data;
    private MachineAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine);
        initViews();
    }
    private void initViews(){
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        loadJSON();
    }
    private void loadJSON(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<ServerResponse> call = request.getJSON();
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {

                ServerResponse jsonResponse = response.body();
                data = new ArrayList<>(Arrays.asList(jsonResponse.getMachinename()));
                adapter = new MachineAdapter(data);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }
}