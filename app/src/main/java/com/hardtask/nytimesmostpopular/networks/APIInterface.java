package com.hardtask.nytimesmostpopular.networks;

import com.hardtask.nytimesmostpopular.dataModels.retrofit.Result;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by it_ah on 07/04/2019.
 */

public interface APIInterface
{
    @GET("/emailed/7.json")
    void getResult(@Query("api-key") String API_KEY,
                   Callback<Response> getResults);
}
