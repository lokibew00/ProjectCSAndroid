package com.example.bewkitsada.loginregistration2.fragment;

import android.app.Notification;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
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
import com.example.bewkitsada.loginregistration2.models.booked_process.Booked;
import com.example.bewkitsada.loginregistration2.models.booked_process.BookedAdapter;
import com.example.bewkitsada.loginregistration2.string.Constants;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

public class BookedHistoryFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<Booked> bookedArrayList;
    private BookedAdapter adapter;


    public BookedHistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = getActivity().getSharedPreferences("first" , Context.MODE_PRIVATE);
        loadjson(preferences.getString(Constants.USER_ID,""));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_booked_history, container, false);
        view.setTag(TAG);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return view;
    }

    public void loadjson(String user_id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
        Booked booked = new Booked();
        booked.setUser_id(user_id);
        ServerRequest serverRequest = new ServerRequest();
        serverRequest.setOperation(Constants.BOOKED_OPERATION);
        serverRequest.setBooked(booked);
        Call<ServerResponse> responseCall = requestInterface.operation(serverRequest);
        responseCall.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse jsonResponse = response.body();
                bookedArrayList = new ArrayList<>(Arrays.asList(jsonResponse.getBooked()));
                adapter = new BookedAdapter(getActivity().getApplicationContext() , bookedArrayList);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.d("failure" , "");
            }
        });

    }
    public void notification(){
        Notification notification = new NotificationCompat.Builder(getContext())
                .setSmallIcon(R.mipmap.ic_launcher).setContentTitle("Test").setContentText("Textasdfasdf")
                .setAutoCancel(true)
                .build();
    }


}
