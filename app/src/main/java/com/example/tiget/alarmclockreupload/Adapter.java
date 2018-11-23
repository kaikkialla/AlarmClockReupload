package com.example.tiget.alarmclockreupload;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ru.rambler.libs.swipe_layout.SwipeLayout;

public class Adapter extends RecyclerView.Adapter<ViewHolder>{
    MainActivity activity;
    private List<Alarm> mAlarms = new ArrayList<>(); // в mAlarms храним данные
    View v;


    private int COUNT;
    private final int[] itemsOffset = new int[COUNT];


    public Adapter(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        COUNT = mAlarms.size();

        LayoutInflater inflater = LayoutInflater.from(activity);
        v = inflater.inflate(R.layout.recycler_view_row, parent, false );
        final ViewHolder viewHolder = new ViewHolder(v);

        //Поставить еще лисенер на весь лайоут
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHolder.swipeLayout.animateReset();
            }
        });

        View.OnClickListener onClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "OnTouch", Toast.LENGTH_SHORT).show();
            }
        };

/*Не надо, т.к нету левой вьюшки
        if (viewHolder.leftView != null) {
            viewHolder.leftView.setClickable(true);
            viewHolder.leftView.setOnClickListener(onClick);
        }
*/

        if (viewHolder.rightView != null) {
            viewHolder.rightView.setClickable(true);
            viewHolder.rightView.setOnClickListener(onClick);
        }

        viewHolder.swipeLayout.setOnSwipeListener(new SwipeLayout.OnSwipeListener() {
            @Override
            public void onBeginSwipe(SwipeLayout swipeLayout, boolean moveToRight) {
            }

            //При достижении предела
            @Override
            public void onSwipeClampReached(SwipeLayout swipeLayout, boolean moveToRight) {
                Toast.makeText(swipeLayout.getContext(),
                        (moveToRight ? "Left" : "Right") + " clamp reached",
                        Toast.LENGTH_SHORT)
                        .show();
                viewHolder.textViewPos.setText("TADA!");
            }

            @Override
            public void onLeftStickyEdge(SwipeLayout swipeLayout, boolean moveToRight) {
            }

            @Override
            public void onRightStickyEdge(SwipeLayout swipeLayout, boolean moveToRight) {
            }
        });


        return viewHolder;

    }


    //Заполняем элементы будильника
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {
        final Alarm alarm = mAlarms.get(position);




        if (alarm.getMinute() == 00) {
            viewHolder.AlarmClockTime.setText(alarm.getHour() + ":00" );
        } else viewHolder.AlarmClockTime.setText(alarm.getHour() + ":" + alarm.getMinute());

    }

    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        if (holder.getAdapterPosition() != RecyclerView.NO_POSITION) {
            itemsOffset[holder.getAdapterPosition()] = holder.swipeLayout.getOffset();
        }
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