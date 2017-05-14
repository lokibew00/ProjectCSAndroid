package com.example.bewkitsada.loginregistration2.models;


import com.example.bewkitsada.loginregistration2.models.machineprocess.MachineName;
import com.example.bewkitsada.loginregistration2.models.userprocess.User;

public class ServerResponse {

    private String result;
    private String message;
    private User user;
    private MachineName[] machinename;

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
}