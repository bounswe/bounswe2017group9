package com.concerter.models;

public class Review {
    long id;
    long userID;
    long concertID;
    String context;
    float rate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public long getConcertID() {
        return concertID;
    }

    public void setConcertID(long concertID) {
        this.concertID = concertID;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
}
