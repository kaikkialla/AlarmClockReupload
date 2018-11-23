package com.example.tiget.alarmclockreupload;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import ru.rambler.libs.swipe_layout.SwipeLayout;

public class ViewHolder extends RecyclerView.ViewHolder {
    View itemView;
    TextView AlarmClockTitle;
    TextView AlarmClockTime;
    CheckBox AlarmClockStatus;
    Context context;


    SwipeLayout swipeLayout;
    TextView textViewPos;
    ImageView imageViewPos;
    View rightView;
    //View leftView;


    public ViewHolder(View itemView) {
        super(itemView);
        this.context = context;


        AlarmClockTitle = itemView.findViewById(R.id.Alarm_Clock_Title);
        AlarmClockTime = itemView.findViewById(R.id.Alarm_Clock_Time);
        AlarmClockStatus = itemView.findViewById(R.id.Alarm_Clock_Status_Checkbox);
        this.itemView = itemView;

        textViewPos = itemView.findViewById(R.id.text_view_pos);
        imageViewPos = itemView.findViewById(R.id.image_view_pos);
        swipeLayout = itemView.findViewById(R.id.swipe_layout);
        rightView = itemView.findViewById(R.id.right_view);
        //leftView = itemView.findViewById(R.id.left_view);





        AlarmClockStatus.setTag("unchecked");
        AlarmClockStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AlarmClockStatus.getTag().equals("unchecked")) {
                    //TODO
                    AlarmClockStatus.setTag("checked");
                } else if(AlarmClockStatus.getTag().equals("checked")){
                    //TODO
                    AlarmClockStatus.setTag("unchecked");
                }
            }
        });



    }

}
