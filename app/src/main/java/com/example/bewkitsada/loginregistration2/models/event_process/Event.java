package com.example.bewkitsada.loginregistration2.models.event_process;

/**
 * Created by bewkitsada on 21/8/2560.
 */

public class Event {
    private String date;
    private String start;
    private String end;
    private String time;
    private String machine_location_id;
    private String user_id;
    private String teacher;
    public void setDate(String date) {
        this.date = date;
    }

    public void setMachine_location_id(String machine_location_id) {
        this.machine_location_id = machine_location_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getTeacher() {
        return teacher;
    }
}
