package com.example.bewkitsada.loginregistration2.models.machine_locationprocess;

/**
 * Created by bewkitsada on 5/5/2560.
 */

public class MachineLocation {
    private String machine_location_id;
    private String machine_statusDetail;
    private String location_room;
    private String location_detail;
    private String location_floor;
    private String machine_id;
    private String machine_nameeng;
    private String location_id;
    private String location_build;
    private String machine_location_picture;

    public String getMachine_id() {
        return machine_id;
    }

    public String getMachine_location_id() {
        return machine_location_id;
    }

    public String getLocation_id() {
        return location_id;
    }

    public String getLocation_build(){
        return location_build;
    }

    public String getLocation_room() {
        return location_room;
    }

    public String getMachine_nameeng() {
        return machine_nameeng;
    }

    public String getLocation_detail() {
        return location_detail;
    }

    public String getLocation_floor() {
        return location_floor;
    }

    public String getMachine_statusDetail() {
        return machine_statusDetail;
    }

    public String getMachine_location_picture() {
        return machine_location_picture;
    }

    public void setMachine_id(String machine_id) {
        this.machine_id = machine_id;
    }

    public void setMachine_location_id(String machine_location_id) {
        this.machine_location_id = machine_location_id;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
