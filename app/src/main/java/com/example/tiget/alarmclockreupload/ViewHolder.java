package com.example.tiget.alarmclockreupload;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder {
    View itemView;
    TextView AlarmClockTitle;
    TextView AlarmClockTime;
    CheckBox AlarmClockStatus;
    Context context;

    public ViewHolder(View itemView) {
        super(itemView);
        this.context = context;

        AlarmClockTitle = itemView.findViewById(R.id.Alarm_Clock_Title);
        AlarmClockTime = itemView.findViewById(R.id.Alarm_Clock_Time);
        AlarmClockStatus = itemView.findViewById(R.id.Alarm_Clock_Status_Checkbox);
        this.itemView = itemView;

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
