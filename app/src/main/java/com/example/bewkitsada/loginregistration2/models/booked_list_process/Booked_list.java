package com.example.bewkitsada.loginregistration2.models.booked_list_process;

/**
 * Created by bewkitsada on 2/9/2560.
 */

public class Booked_list {
    private String start;
    private String end;
    private String start_date;
    private String end_date;
    private String machine_nameeng;
    private String name;
    private String date;

    public void setDate(String date) {
        this.date = date;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getMachine_nameeng() {
        return machine_nameeng;
    }

    public String getName() {
        return name;
    }

    public String getEnd_date() {
        return end_date;
    }

    public String getStart_date() {
        return start_date;
    }
}
