package com.example.bewkitsada.loginregistration2.models;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RequestInterface {

    @POST("android_api/server/")
    Call<ServerResponse> operation(@Body ServerRequest request);

    @GET("android_api/server/testjson.php")
    Call<ServerResponse> getJSON();
}
