package com.hardtask.nytimesmostpopular.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hardtask.nytimesmostpopular.R;
import com.hardtask.nytimesmostpopular.dataModels.retrofit.MediaMetadatum;
import com.hardtask.nytimesmostpopular.dataModels.retrofit.Result;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by it_ah on 08/04/2019.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    public ArrayList<Result>resultArrayList ;

//    public ArrayList<MediaMetadatum>mediaArrayList;

    public Context context ;

    public final OnClick listner ;

    public interface OnClick
    {
         void onClick(Result item);
    }

    public RecyclerViewAdapter(ArrayList<Result> resultArrayList, Context context, OnClick listner) {
        this.resultArrayList = resultArrayList;
        this.context = context;
        this.listner = listner;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.custom_design_home,null,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {

        holder.bindData(resultArrayList.get(position),listner);
    }

    @Override
    public int getItemCount() {
        return resultArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView imageView ;

        TextView titleHeader ;

        TextView abstractTitle ;

        TextView calendarText ;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = (CircleImageView) itemView.findViewById(R.id.catImageCustomHomeLayout);

            titleHeader = (TextView)itemView.findViewById(R.id.catTitleCustomHomeLayout);

            abstractTitle = (TextView)itemView.findViewById(R.id.catAbstractCustomHomeLayout);

            calendarText = (TextView)itemView.findViewById(R.id.catDateCustomHomeLayout);


        }

        private void bindData(final Result result, final OnClick listner)
        {
            titleHeader.setText(result.getTitle());

            abstractTitle.setText(result.getAbstract());

            calendarText.setText(result.getPublishedDate());

//            Glide.with(context).load(result.getMedia().get(0).getMediaMetadata().get(0).getUrl()).into(imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listner.onClick(result);
                }
            });
        }

    }
}
