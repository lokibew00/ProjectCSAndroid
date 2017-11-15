package com.example.bewkitsada.loginregistration2.models.userprocess;


public class User {

    private String name;
    private String username;
    private String unique_id;
    private String password;
    private String old_password;
    private String new_password;
    private String user_statusID;
    private String user_statusDetail;
    private String user_id;


    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getUnique_id() {
        return unique_id;
    }

    public String getUser_statusDetail(){ return user_statusDetail;}
    public String getUser_statusID(){ return user_statusID; }

    public String getUser_id() {
        return user_id;
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
//
//    public void setStatus_userDetail(String user_statusDetail) {
//        this.user_statusDetail = user_statusDetail;
//    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

}
