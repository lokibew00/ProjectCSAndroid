package com.example.bewkitsada.loginregistration2.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bewkitsada.loginregistration2.R;
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

import static android.content.ContentValues.TAG;

/**
 * Created by bewkitsada on 5/5/2560.
 */

public class MachineNameFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<MachineName> data;
    private MachineAdapter adapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize dataset, this data would usually come from a local content provider or
        // remote server.
        loadJSON();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_machine_name, container, false);
        view.setTag(TAG);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        adapter = new MachineAdapter()

        return view;
    }

//    public void initFragment(View view){
//        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
//        recyclerView.setHasFixedSize(true);
//    }

    private void loadJSON() {
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
                Log.d("Error", t.getMessage());
            }
        });
    }
}
