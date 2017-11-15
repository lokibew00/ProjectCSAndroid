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
import com.example.bewkitsada.loginregistration2.models.ServerRequest;
import com.example.bewkitsada.loginregistration2.models.ServerResponse;
import com.example.bewkitsada.loginregistration2.models.machine_locationprocess.MachineListAdapter;
import com.example.bewkitsada.loginregistration2.models.machine_locationprocess.MachineLocation;
import com.example.bewkitsada.loginregistration2.string.Constants;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

/**
 * Created by bewkitsada on 5/5/2560.
 */

public class MachineListFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<MachineLocation> data;
    private MachineListAdapter adapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize dataset, this data would usually come from a local content provider or
        // remote server.
        Bundle bundle = this.getArguments();
        loadJSON(bundle.getString(Constants.MACHINE_ID));
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

    private void loadJSON(String machine_id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
        MachineLocation ml = new MachineLocation();
        ml.setMachine_id(machine_id);
//        MachineName machineName = new MachineName();
//        machineName.setMachine_id(machine_id);
        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.MACHINE_ID);
        request.setMachineLocation(ml);
        Call<ServerResponse> response = requestInterface.operation(request);
        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                ServerResponse jsonResponse = response.body();
//                Snackbar.make(getView(), "Response", Snackbar.LENGTH_LONG).show();


                data = new ArrayList<>(Arrays.asList(jsonResponse.getMachineLocations()));
                adapter = new MachineListAdapter(getActivity().getApplicationContext() , data);
                recyclerView.setAdapter(adapter);
                for (MachineLocation ml : data){
                    Log.d("data" , ml.toString());
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }
}
