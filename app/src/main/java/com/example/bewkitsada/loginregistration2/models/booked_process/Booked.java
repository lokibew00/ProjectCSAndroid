package com.example.bewkitsada.loginregistration2.models.booked_process;

/**
 * Created by bewkitsada on 1/9/2560.
 */

public class Booked {
    private String start_date;
    private String end_date;
    private String event_status_detail;
    private String machine_nameeng;
    private String user_id;
    private String name;

    public String getName() {
        return name;
    }

    public String getMachine_nameeng() {
        return machine_nameeng;
    }

    public String getEnd_date() {
        return end_date;
    }

    public String getEvent_status_detail() {
        return event_status_detail;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
