package com.example.bewkitsada.loginregistration2.fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bewkitsada.loginregistration2.R;
import com.example.bewkitsada.loginregistration2.models.RequestInterface;
import com.example.bewkitsada.loginregistration2.models.ServerResponse;
import com.example.bewkitsada.loginregistration2.string.Constants;

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

    protected RecyclerView recyclerView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadJSON();
        // Initialize dataset, this data would usually come from a local content provider or
        // remote server.

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_machine_name, container, false);
        view.setTag(TAG);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        return view;
    }

    private void loadJSON(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<ServerResponse> call = request.getJSON();
        call.enqueue(new Callback<ServerResponse>(){
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {

                ServerResponse resp = response.body();
                Snackbar.make(getView(),resp.getMessage(), Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }
}
