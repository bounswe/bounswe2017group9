package com.example.abtasdan.listviewtutorial;

/**
 * Created by abtasdan on 24.10.2017.
 */

public class Concert {

    String artist;
    String location;
    String price;
    String date;
    String info;
    String imageUrl;

    int numberOfComment;
    int numberOfAttendee;

    public Concert(String artist, String location, String price, String date, String info, String imageUrl, int numberOfComment, int numberOfAttendee) {
        this.artist = artist;
        this.location = location;
        this.price = price;
        this.date = date;
        this.info = info;
        this.imageUrl = imageUrl;
        this.numberOfComment = numberOfComment;
        this.numberOfAttendee = numberOfAttendee;
    }


    public Concert() {
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getNumberOfComment() {
        return numberOfComment;
    }

    public void setNumberOfComment(int numberOfComment) {
        this.numberOfComment = numberOfComment;
    }

    public int getNumberOfAttendee() {
        return numberOfAttendee;
    }

    public void setNumberOfAttendee(int numberOfAttendee) {
        this.numberOfAttendee = numberOfAttendee;
    }
}
