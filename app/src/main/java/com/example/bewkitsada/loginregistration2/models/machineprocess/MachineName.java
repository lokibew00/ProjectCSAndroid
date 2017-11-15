package com.example.bewkitsada.loginregistration2.models.machineprocess;

import com.example.bewkitsada.loginregistration2.models.machine_locationprocess.MachineLocation;

import java.util.List;

/**
 * Created by bewkitsada on 5/5/2560.
 */

public class MachineName {
    private String machine_id;
    private String machine_namethai;
    private String machine_nameeng;
    private String machine_detail_how_to;
    private String machine_rule_detail;
    private String machine_picture;
    private List<MachineLocation> machineLocations;


    public String getMachine_id() {
        return machine_id;
    }

    public String getMachinenamethai() {
        return machine_namethai;
    }

    public String getMachinenameeng() {
        return machine_nameeng;
    }

    public String getMachine_detail_how_to() {
        return machine_detail_how_to;
    }

    public String getMachine_rule_detail() {
        return machine_rule_detail;
    }

    public String getMachine_picture() {
        return machine_picture;
    }

    public void setMachine_id(String machine_id) {
        this.machine_id = machine_id;
    }

    public List<MachineLocation> getMachineLocationsList() {
        return machineLocations;
    }

}
