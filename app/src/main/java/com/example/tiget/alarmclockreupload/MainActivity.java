package com.example.tiget.alarmclockreupload;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import static android.view.ViewTreeObserver.*;

public class MainActivity extends AppCompatActivity {

    FrameLayout Frame_Layout;
    public static long SCREEN_WIDTH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_holder);
        Frame_Layout = findViewById(R.id.Frame_Layout);


        Frame_Layout.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                //Получаем ширину экрана в пикселях
                Frame_Layout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                SCREEN_WIDTH = Frame_Layout.getWidth();

            }
        });


        //При первом запуске открываем фрагмент с будильниками
        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.Frame_Layout, new AlarmClockFragment(), null).commit();
        }
    }

}
