package com.hardtask.nytimesmostpopular.classes;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * Created by it_ah on 08/04/2019.
 */

public class CheckInternetConnection
{
    Context context ;

    public CheckInternetConnection(Context context) {
        this.context = context;
    }

    public  boolean IsConnected ()
    {
        ConnectivityManager CM = (ConnectivityManager)context.getSystemService(CONNECTIVITY_SERVICE);
        if (CM != null)
        {
            NetworkInfo NF = CM.getActiveNetworkInfo();

            if (NF != null)
            {
                if (NF.getState() == NetworkInfo.State.CONNECTED)
                {
                    return true ;
                }
            }
        }
        return false ;
    }
}
