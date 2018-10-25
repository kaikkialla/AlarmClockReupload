package com.example.tiget.alarmclockreupload;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class AlarmClockFragment extends Fragment {

    BottomNavigationView bottomNavigationView;
    ImageView AddAlarmButton;
    Adapter adapter;
    List<Alarm> alarms;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final Context context = getContext();
        View view = inflater.inflate(R.layout.alarm_clock_fragment_layout, container, false);
        bottomNavigationView = view.findViewById(R.id.bottom_navigation_view);
        AddAlarmButton = view.findViewById(R.id.Add_Alarm_Button);


        //Настраиваем Recycler View
        adapter = new Adapter((MainActivity) getActivity());
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 1));



        //Настраиваем Bottom Navigation View
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_sleeplab:
                                break;
                            case R.id.action_alarm:

                                break;
                            case R.id.action_settings:

                                break;
                        }
                        return false;
                    }
                });

        AddAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AddAlarmDialog fragment = new AddAlarmDialog();
                fragment.show(getFragmentManager(), AddAlarmDialog.TAG);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        Database.setChangeListener(new Database.ChangeListener() {
            @Override
            public void onChange(List<Alarm> alarms) {
                adapter.swap(alarms);
            }
        });
        adapter.swap(Database.getAlarms());
    }
}
