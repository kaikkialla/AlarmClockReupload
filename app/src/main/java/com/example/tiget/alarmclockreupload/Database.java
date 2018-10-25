package com.example.tiget.alarmclockreupload;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private Context mContext;
    private static List<Alarm> mAlarms = new ArrayList<>();

    /**
     * Переменная хранит слушатель изменений в базе данных.
     */
    private static ChangeListener mChangeListener = null;

    public Database(Context context) {
        mContext = context;
    }

    /**
     * Метод загружает из локального хранилища сохранённые ранее будильники.
     */
    public void load() {
        // получаем будильники в виде строки
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        final String s = preferences.getString("ALARMS", "");
        // десериализуем строку
        final Gson gson = new Gson();
        mAlarms = gson.fromJson(s, new TypeToken<List<Alarm>>(){}.getType());
        if (mChangeListener != null) {
            mChangeListener.onChange(mAlarms);
        }
    }

    /**
     * Метод сохраняет в локальное хранилище будильники из mAlarms.
     */
    private void save() {
        // открываем для редактирования SP
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        final SharedPreferences.Editor editor = preferences.edit();
        // превращаем в строку (сериализуем)
        final Gson gson = new Gson();
        final String s = gson.toJson(mAlarms, new TypeToken<List<Alarm>>(){}.getType());
        // сохраняем по ключу ALARMS
        editor.putString("ALARMS", s);
        editor.apply();
    }

    /**
     * Метод добавляет будильник в базу данных (и сохраняет изменения в локальное хранилище).
     * @param alarm Будильник, который нужно добавить.
     */
    public void addAlarm(Alarm alarm) {
        mAlarms.add(alarm);
        if (mChangeListener != null) {
            mChangeListener.onChange(mAlarms);
        }
        save();
    }

    /**
     * Метод удаляет из базы данных будильник по id (и сохраняет изменения в локальное хранилище).
     * @param id Идентификатор будильника, который нужно удалить.
     */
    public void removeAlarm(long id) {
        boolean needsSave = false;
        for (int i = 0; i < mAlarms.size(); ++i) {
            if (mAlarms.get(i).getId() == id) {
                mAlarms.remove(i);
                needsSave = true;
                break;
            }
        }

        if (needsSave) {
            if (mChangeListener != null) {
                mChangeListener.onChange(mAlarms);
            }
            save();
        }
    }

    /**
     * Возвращает список будильников.
     */
    public static List<Alarm> getAlarms() {
        return mAlarms;
    }

    /**
     * Очищает будильники (только из оперативной памяти!).
     */
    public void clear() {
        mAlarms.clear();
        if (mChangeListener != null) {
            mChangeListener.onChange(mAlarms);
        }
    }

    public static void setChangeListener(ChangeListener listener) {
        mChangeListener = listener;
    }

    interface ChangeListener {
        /**
         * Будет вызываться после каждого изменения списка будильников.
         * @param alarms Актуальный список будильников.
         */
        void onChange(List<Alarm> alarms);
    }

}