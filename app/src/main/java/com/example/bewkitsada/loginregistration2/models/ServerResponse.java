package com.example.bewkitsada.loginregistration2.models;


import com.example.bewkitsada.loginregistration2.models.booked_list_process.Booked_list;
import com.example.bewkitsada.loginregistration2.models.booked_process.Booked;
import com.example.bewkitsada.loginregistration2.models.event_process.Event;
import com.example.bewkitsada.loginregistration2.models.machine_locationprocess.MachineLocation;
import com.example.bewkitsada.loginregistration2.models.machineprocess.MachineName;
import com.example.bewkitsada.loginregistration2.models.userprocess.User;

public class ServerResponse {

    private String result;
    private String message;
    private User user;
    private MachineName[] machinename;
    private MachineLocation[] machineLocations;
    private Booked[] booked;
    private Event event;
    private Booked_list[] booked_list;

    public String getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }

    public MachineName[] getMachinename() {
        return machinename;
    }

    public MachineLocation[] getMachineLocations(){ return machineLocations; }

    public Booked[] getBooked() {
        return booked;
    }

    public Event getEvent() {
        return event;
    }

    public Booked_list[] getBooked_list() {
        return booked_list;
    }
}