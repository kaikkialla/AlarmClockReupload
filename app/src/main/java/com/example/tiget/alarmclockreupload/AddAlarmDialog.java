package com.example.tiget.alarmclockreupload;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


public class AddAlarmDialog extends DialogFragment {

    private static final int MIN_TEXT_LENGTH = 4;
    private static final String EMPTY_STRING = "";
    public static final String TAG = AddAlarmDialog.class.getSimpleName();

    private TextInputLayout mTextInputLayout;
    private EditText mEditText;
    private TimePicker mTimePicker;
    private Dialog dialog;
    public static String AlarmTitle;
    public static int AlarmHour;
    public static int AlarmMinute;
    Context context;




    @RequiresApi(api = Build.VERSION_CODES.M)
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        context = getContext();
        // создаём кастомную вьюху
        final View view = getActivity().getLayoutInflater().inflate(R.layout.add_alarm_dialog_custom_layout, null);
        //Создаем создателя диалогов
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //Создаем создателя вьюшек
        LayoutInflater inflater = getActivity().getLayoutInflater();

        //Прикрепляем наш макет к диалогу
        builder.setView(inflater.inflate(R.layout.add_alarm_dialog_custom_layout, null));

        mTextInputLayout = view.findViewById(R.id.textInputLayout);
        mEditText = view.findViewById(R.id.editText);
        mTextInputLayout.setHint("Введите название");
        mTimePicker = view.findViewById(R.id.timePicker);

        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        mTimePicker.setIs24HourView(true);

        // настраиваем диалог
        builder.setTitle("Добавление будильника");
        builder.setPositiveButton("Добавить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AlarmTitle = mTextInputLayout.getEditText().getText().toString();
                AlarmHour = mTimePicker.getHour();
                AlarmMinute = mTimePicker.getMinute();
                Log.e("time", AlarmTitle + "  " + AlarmHour + ":" + AlarmMinute);
                Toast.makeText(context, AlarmTitle +  AlarmHour + AlarmMinute, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //cancel
            }
        });



        // создаём диалог и отдаём наружу
        return builder.create();




    }

}
