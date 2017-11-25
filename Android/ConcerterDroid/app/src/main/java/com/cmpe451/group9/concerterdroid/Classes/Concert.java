package com.cmpe451.group9.concerterdroid.Classes;

import android.support.v4.util.Pair;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Necip on 18.11.2017.
 */

public class Concert implements Serializable {
    private int id;
    private String name;
    private User owner;
    private Artist artist;
    private Location location;
    private String dateTime;
    //TODO After tag implemenetation change this.
    private ArrayList<String> semanticTags;
    //TODO Price range variable should be changed(my mistake :( ).
    private int priceRange;
    private float rate;
    private int voteraAmount;
    private ArrayList<Comment> comments;
    private ArrayList<User> attendees;
    private String imagePath;

    public Concert(){

    }

    public Concert( String name, Artist artist, Location location, String dateTime, int priceRange) {
        this.name = name;
        this.artist = artist;
        this.location = location;
        this.dateTime = dateTime;
        this.priceRange = priceRange;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public ArrayList<String> getSemanticTags() {
        return semanticTags;
    }

    public void setSemanticTags(ArrayList<String> semanticTags) {
        this.semanticTags = semanticTags;
    }

    public int getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(int priceRange) {
        this.priceRange = priceRange;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public int getVoteraAmount() {
        return voteraAmount;
    }

    public void setVoteraAmount(int voteraAmount) {
        this.voteraAmount = voteraAmount;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public ArrayList<User> getAttendees() {
        return attendees;
    }

    public void setAttendees(ArrayList<User> attendees) {
        this.attendees = attendees;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
