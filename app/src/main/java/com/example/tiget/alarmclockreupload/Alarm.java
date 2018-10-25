package com.example.tiget.alarmclockreupload;

public class Alarm {
    final String title;
    private final int hour;
    private final int minute;
    final long id;

    public Alarm(long id, String title, int hour, int minute) {
        this.title = title;
        this.hour =  hour;
        this.minute =  minute;
        this.id = id;

    }


    public String getTitle() {
        return title;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }


    public long getId() {
        return id;
    }

}
