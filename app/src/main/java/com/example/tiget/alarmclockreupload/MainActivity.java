package com.example.tiget.alarmclockreupload;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    FrameLayout Frame_Layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_holder);
        Frame_Layout = findViewById(R.id.Frame_Layout);


        //При первом запуске открываем фрагмент с будильниками
        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.Frame_Layout, new AlarmClockFragment(), null).commit();
        }
    }
}
