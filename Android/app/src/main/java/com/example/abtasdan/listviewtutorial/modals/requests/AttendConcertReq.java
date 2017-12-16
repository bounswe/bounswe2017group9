package com.example.abtasdan.listviewtutorial.modals.requests;

/**
 * Created by abtasdan on 6.12.2017.
 */

public class AttendConcertReq {
    int user_id;
    String status;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
