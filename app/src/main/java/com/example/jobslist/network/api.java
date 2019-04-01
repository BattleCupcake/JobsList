package com.example.jobslist.network;

import com.example.jobslist.models.PersonsList;
import retrofit2.Call;
import retrofit2.http.GET;

public interface api {

    @GET("65gb/static/raw/master/testTask.json")
    Call<PersonsList> getData();

}