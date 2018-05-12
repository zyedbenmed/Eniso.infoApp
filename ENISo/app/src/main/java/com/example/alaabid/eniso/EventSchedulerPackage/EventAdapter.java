package com.example.alaabid.eniso.EventSchedulerPackage;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alaabid.eniso.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

/**
 * Created by Ala Abid on 23/03/2018.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder>{

    private Context mCtx;
    private List<EventModel> eventList;
    private OnRVItemClickListener mListener;

    public interface OnRVItemClickListener{
        void onRVItemClick(int position);
        void onDeleteClick(int position);
        void onEditClick(int position);
    }

    public void setOnRVItemClickListener(OnRVItemClickListener listener){
        mListener=listener;
    }

    public EventAdapter(Context mContext, List<EventModel> eventList) {
        this.mCtx = mContext;
        this.eventList = eventList;
    }
    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.event_layout, null);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        EventModel event = eventList.get(position);
        try {
            holder.tv_date.setText(DateFormat.getDateInstance().format(new SimpleDateFormat("yyyy-MM-dd").parse(event.getDate())));
        }
        catch (Exception ex){

        }
        String hh=event.getHour();
        if(hh.length()>8)holder.tv_hour.setText(hh.substring(0,8));
        else if(hh.length()==3)holder.tv_hour.setText("0"+hh);
        else holder.tv_hour.setText(hh);
        holder.tv_desc.setText(event.getDescr());
        holder.tv_title.setText(event.getTitle());
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        holder.cardy.setBackgroundColor(color);
        //EDIT later, if user is author
        if(event.getTitle().equals("rat")){
            holder.bt_close.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    class EventViewHolder extends RecyclerView.ViewHolder{
        TextView tv_desc, tv_title, tv_hour, tv_date;
        CardView cardy;
        ImageView bt_close, bt_edit;
        public EventViewHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.eventTitle);
            tv_date = itemView.findViewById(R.id.eventDate);
            tv_desc = itemView.findViewById(R.id.eventDesc);
            tv_hour = itemView.findViewById(R.id.eventHour);
            cardy = itemView.findViewById(R.id.cardView);
            bt_close = itemView.findViewById(R.id.ibt_close);
            bt_edit = itemView.findViewById(R.id.ibt_edit);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mListener!=null){
                        int pos = getAdapterPosition();
                        if(pos !=RecyclerView.NO_POSITION){
                            mListener.onRVItemClick(pos);
                        }

                    }

                }
            });
            bt_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mListener!=null){
                        int pos = getAdapterPosition();
                        if(pos !=RecyclerView.NO_POSITION){
                            mListener.onDeleteClick(pos);
                        }

                    }
                }
            });
            bt_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mListener!=null){
                        int pos = getAdapterPosition();
                        if(pos !=RecyclerView.NO_POSITION){
                            mListener.onEditClick(pos);
                        }

                    }
                }
            });
        }
    }

}
