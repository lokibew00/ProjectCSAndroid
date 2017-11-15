package com.example.bewkitsada.loginregistration2.fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.bewkitsada.loginregistration2.R;
import com.example.bewkitsada.loginregistration2.models.RequestInterface;
import com.example.bewkitsada.loginregistration2.models.ServerRequest;
import com.example.bewkitsada.loginregistration2.models.ServerResponse;
import com.example.bewkitsada.loginregistration2.models.booked_list_process.BookedListAdapter;
import com.example.bewkitsada.loginregistration2.models.booked_list_process.Booked_list;
import com.example.bewkitsada.loginregistration2.models.booked_process.Booked;
import com.example.bewkitsada.loginregistration2.string.Constants;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

public class BookedListFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<Booked> bookedArrayList;
    private BookedListAdapter adapter;
    private String start , end , date;
    private ProgressBar progressBar;


    public BookedListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        date = bundle.getString("year") + "-" + bundle.getString("month") + "-" + bundle.getString("day");
        start = date + " " + String.format("%02d:%02d", 00, 00);
        end = date + " " + String.format("%02d:%02d", 23, 59);
        Log.i("data" , start + "\n" + end + "\n" + date);
        loadjson(start , end);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_booked_list, container, false);
        view.setTag(TAG);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return view;
    }

    public void loadjson(String start , String end) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
        Booked_list booked_list = new Booked_list();
        booked_list.setStart(start);
        booked_list.setEnd(end);
        ServerRequest serverRequest = new ServerRequest();
        serverRequest.setOperation(Constants.BOOKED_LIST_OPERATION);
        serverRequest.setBooked_list(booked_list);
        Call<ServerResponse> responseCall = requestInterface.operation(serverRequest);
        responseCall.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse jsonResponse = response.body();
                bookedArrayList = new ArrayList<>(Arrays.asList(jsonResponse.getBooked()));
                adapter = new BookedListAdapter(getActivity().getApplicationContext() , bookedArrayList);
                recyclerView.setAdapter(adapter);
                Snackbar.make(getView() , "Success" , Snackbar.LENGTH_SHORT).show();
                Log.d("Success" ,"");


            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.e("failure" , "");
            }
        });

    }

    public void loadjson(String date){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
        Booked_list booked_list = new Booked_list();
        booked_list.setDate(date);
        ServerRequest serverRequest = new ServerRequest();
        serverRequest.setOperation("bookedtest");
        serverRequest.setBooked_list(booked_list);
        Call<ServerResponse> responseCall = requestInterface.operation(serverRequest);
        responseCall.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse jsonResponse = response.body();
                bookedArrayList = new ArrayList<>(Arrays.asList(jsonResponse.getBooked()));
                adapter = new BookedListAdapter(getActivity().getApplicationContext() , bookedArrayList);
                recyclerView.setAdapter(adapter);
                Snackbar.make(getView() , "Success" , Snackbar.LENGTH_SHORT).show();
                Log.d("Success" ,"");


            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.e("failure" , "");
            }
        });


    }



}
