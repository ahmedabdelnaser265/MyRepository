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
import com.hardtask.nytimesmostpopular.dataModels.DataModelResult;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by it_ah on 07/04/2019.
 */

public class RecyclerViewAdapterMainHome extends RecyclerView.Adapter<RecyclerViewAdapterMainHome.ViewHolder>{

    public Context context ;

    public ArrayList<DataModelResult>resultArrayList ;

    public final OnClick listner;

    public interface OnClick
    {
        void onClick (DataModelResult item);
    }

    public RecyclerViewAdapterMainHome(Context context, ArrayList<DataModelResult> resultArrayList, OnClick listner) {
        this.context = context;
        this.resultArrayList = resultArrayList;
        this.listner = listner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.custom_design_home,null,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

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

        private void bindData(final DataModelResult itemResult, final OnClick listner)
        {

            Glide.with(context).load(itemResult.getuRLImage()).into(imageView);

            titleHeader.setText(itemResult.getTitleData());

            abstractTitle.setText(itemResult.getAbstractData());

            calendarText.setText(itemResult.getCalendarData());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listner.onClick(itemResult);
                }
            });


        }

    }
}
