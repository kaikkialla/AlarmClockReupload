package com.example.tiget.alarmclockreupload;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<ViewHolder>{
    MainActivity activity;
    private List<Alarm> mAlarms = new ArrayList<>(); // в mAlarms храним данные

    public Adapter(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        View v = inflater.inflate(R.layout.recycler_view_row, parent, false );
        ViewHolder vh = new ViewHolder(v);


        return vh;

    }


    //Заполняем элементы будильника
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final Alarm alarm = mAlarms.get(position);

        if (alarm.getMinute() == 00) {
            viewHolder.AlarmClockTime.setText(alarm.getHour() + ":00" );
        } else viewHolder.AlarmClockTime.setText(alarm.getHour() + ":" + alarm.getMinute());

    }


    @Override
    public int getItemCount() {
        return mAlarms.size();

    }


    /**
     * Заполняет внутренний массив будильников массивом из параметров.
     */
    public void swap(List<Alarm> alarms) {
        if (alarms != null) {
            mAlarms.clear();
            mAlarms.addAll(alarms);
            notifyDataSetChanged();
        }
    }




}