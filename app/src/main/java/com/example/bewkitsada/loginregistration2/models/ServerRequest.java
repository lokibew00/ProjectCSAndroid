package com.example.bewkitsada.loginregistration2.models;


import com.example.bewkitsada.loginregistration2.models.booked_list_process.Booked_list;
import com.example.bewkitsada.loginregistration2.models.booked_process.Booked;
import com.example.bewkitsada.loginregistration2.models.event_process.Event;
import com.example.bewkitsada.loginregistration2.models.machine_locationprocess.MachineLocation;
import com.example.bewkitsada.loginregistration2.models.machineprocess.MachineName;
import com.example.bewkitsada.loginregistration2.models.userprocess.User;

public class ServerRequest {

    private String operation;
    private User user;
    private MachineLocation machineLocation;
    private MachineName machineName;
    private Event event;
    private Booked booked;
    private Booked_list booked_list;

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setMachineLocation(MachineLocation machineLocation) {
        this.machineLocation = machineLocation;
    }

    public void setMachineName(MachineName machineName) {
        this.machineName = machineName;
    }
    public void setEvent(Event event){
        this.event = event;
    }

    public void setBooked(Booked booked) {
        this.booked = booked;
    }

    public void setBooked_list(Booked_list booked_list) {
        this.booked_list = booked_list;
    }
}
