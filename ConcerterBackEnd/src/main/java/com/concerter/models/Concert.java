package com.concerter.models;

import org.joda.time.DateTime;

import java.util.List;

public class Concert {
    long id;
    String name;
    User owner;
    String artistName;
    Location location;
    DateTime dateTime;
    List<SemanticTag> semanticTags;
    //Pair<Float> pricaRange;
    float rate;
    List<Comment> comments;
    List<User> attendees;
    String imagePath;
    //List<Report> reports;
    List<Integer> approved;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public List<SemanticTag> getSemanticTags() {
        return semanticTags;
    }

    public void setSemanticTags(List<SemanticTag> semanticTags) {
        this.semanticTags = semanticTags;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<User> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<User> attendees) {
        this.attendees = attendees;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public List<Integer> getApproved() {
        return approved;
    }

    public void setApproved(List<Integer> approved) {
        this.approved = approved;
    }
}
