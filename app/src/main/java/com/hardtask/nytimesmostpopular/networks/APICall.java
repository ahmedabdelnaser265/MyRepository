package com.hardtask.nytimesmostpopular.networks;

import retrofit.RestAdapter;

/**
 * Created by it_ah on 07/04/2019.
 */

public class APICall
{
    static APIInterface apiInterface = null ;

    public static synchronized APIInterface getApiInterface ()
    {
        if (apiInterface ==null)
        {

            RestAdapter adapter = new RestAdapter.Builder()
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setEndpoint(BaseUrls.BASEURL)
                    .build();

            apiInterface = adapter.create(APIInterface.class);

        }

        return apiInterface ;
    }
}
