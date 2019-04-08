package com.hardtask.nytimesmostpopular.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hardtask.nytimesmostpopular.R;
import com.hardtask.nytimesmostpopular.adapters.RecyclerViewAdapterMainHome;
import com.hardtask.nytimesmostpopular.classes.CheckInternetConnection;
import com.hardtask.nytimesmostpopular.classes.VolleySingleton;
import com.hardtask.nytimesmostpopular.dataModels.DataModelResult;
import com.hardtask.nytimesmostpopular.dataModels.retrofit.Result;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {


    public Context context ;

    ArrayList<DataModelResult> resultArrayList ;

    ArrayList<Result>resultRetrofitArr ;

    RecyclerView recyclerView ;

    SpotsDialog spotsDialog ;

    CheckInternetConnection connection ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_blank, container, false);

        context = container.getContext();

        //data

        connection=new CheckInternetConnection(context);

        initViews(mView);

        spotsDialog = new SpotsDialog(context,R.style.Custom);
        resultArrayList = new ArrayList<>();

        resultRetrofitArr = new ArrayList<>();

        if (connection.IsConnected())
        {
            getResult("4eovZaKryL5QUr2FSwx3vbMX6HM5i4bE");

            //        getResultRetrofit()
        }
        else
        {
            Toast.makeText(context, "Please check Internet Connection", Toast.LENGTH_SHORT).show();
        }


        return mView ;
    }

    public void initViews(View view)
    {
        recyclerView =(RecyclerView)view.findViewById(R.id.recyclerViewHome);
    }

    private void getResult (String key)
    {
        spotsDialog.show();

        String url = "https://api.nytimes.com/svc/mostpopular/v2/emailed/7.json?api-key="+key;

        StringRequest request = new StringRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("responseData",response);

                spotsDialog.dismiss();

                try {
                    JSONObject MainObject = new JSONObject(response);

                    String status = MainObject.getString("status");

                    if (status.equals("OK"))
                    {
                        JSONArray getArray = MainObject.getJSONArray("results");

                        for (int i=0 ; i<getArray.length(); i++)
                        {
                            JSONObject getResult = getArray.getJSONObject(i);

                            String title = getResult.getString("title");

                            String Titleabstract = getResult.getString("abstract");

                            String calendar = getResult.getString("published_date");


                            //getJsonArray
                            JSONArray getMedia = getResult.getJSONArray("media");

                            for (int j = 0 ; j<getMedia.length(); j++)

                            {
                                JSONObject getMediaDataJson = getMedia.getJSONObject(j);

                                JSONArray GetMediaMidata = getMediaDataJson.getJSONArray("media-metadata");

                                for (int k = 0 ; k<GetMediaMidata.length(); k++)
                                {
                                    JSONObject getMediaMideDataJsonObject = GetMediaMidata.getJSONObject(k);

                                    String pathUrl = getMediaMideDataJsonObject.getString("url");

                                    DataModelResult result = new DataModelResult(title,Titleabstract,calendar,pathUrl);

                                    resultArrayList.add(result);

                                }
                            }
                        }

                        recyclerView.setItemAnimator(new DefaultItemAnimator());

                        recyclerView.setLayoutManager(new LinearLayoutManager(context));

                        RecyclerViewAdapterMainHome adapter =

                                new RecyclerViewAdapterMainHome(context, resultArrayList, new RecyclerViewAdapterMainHome.OnClick() {
                                    @Override
                                    public void onClick(DataModelResult item) {

                                    }
                                });

                        recyclerView.setAdapter(adapter);

                        adapter.notifyDataSetChanged();
                        adapter.notifyItemChanged(resultArrayList.size());

                    }
                    else {
                        Toast.makeText(context, "Error Get Code!", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                spotsDialog.dismiss();

                Toast.makeText(context, "Error Get Result "+error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        VolleySingleton.getInstance(context).addToRequestQueue(request);

    }


    private void getResultRetrofit()
    {
//        APICall.getApiInterface().getResult("4eovZaKryL5QUr2FSwx3vbMX6HM5i4bE", new Callback<Response>() {
//            @Override
//            public void success(Response response, Response response2) {
//
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//
//            }
//        });
//
//
    }

}
