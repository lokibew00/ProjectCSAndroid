package com.example.bewkitsada.loginregistration2.models.userprocess;


public class User {

    private String name;
    private String username;
    private String unique_id;
    private String password;
    private String old_password;
    private String new_password;


    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getUnique_id() {
        return unique_id;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setOld_password(String old_password) {
        this.old_password = old_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

}
