package com.example.abtasdan.listviewtutorial.modals;

import java.util.Date;

/**
 * Created by abtasdan on 4.12.2017.
 */

public class CreateConcertObject {

    String name;
    long created_by_id;
    ArtistForConcertObject artist;
    LocationObject location;
    Date date_time;
    String date_str;
    String time_str;
    int min_price;
    int max_price;
    String image_path;

    public String getDate_str() {
        return date_str;
    }

    public void setDate_str(String date_str) {
        this.date_str = date_str;
    }

    public String getTime_str() {
        return time_str;
    }

    public void setTime_str(String time_str) {
        this.time_str = time_str;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCreated_by_id() {
        return created_by_id;
    }

    public void setCreated_by_id(long created_by_id) {
        this.created_by_id = created_by_id;
    }

    public ArtistForConcertObject getArtist() {
        return artist;
    }

    public void setArtist(ArtistForConcertObject artist) {
        this.artist = artist;
    }

    public LocationObject getLocation() {
        return location;
    }

    public void setLocation(LocationObject location) {
        this.location = location;
    }

    public Date getDate_time() {
        return date_time;
    }

    public void setDate_time(Date date_time) {
        this.date_time = date_time;
    }

    public int getMin_price() {
        return min_price;
    }

    public void setMin_price(int min_price) {
        this.min_price = min_price;
    }

    public int getMax_price() {
        return max_price;
    }

    public void setMax_price(int max_price) {
        this.max_price = max_price;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }
}
