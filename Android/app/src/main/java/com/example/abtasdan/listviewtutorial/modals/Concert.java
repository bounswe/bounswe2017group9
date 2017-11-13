package com.example.abtasdan.listviewtutorial.modals;



/**
 * Created by abtasdan on 24.10.2017.
 */

public class Concert {
    long id;
    String name;
    CreatedBy created_by;
    long created_by_id;
    ArtistObject artist;
    LocationObject location;
    String date_time;
    long artist_id;
    long location_id;
    int min_price;
    int max_price;
    double rate;
    int voter_amount;
    String image_path;

    public CreatedBy getCreated_by() {
        return created_by;
    }

    public void setCreated_by(CreatedBy created_by) {
        this.created_by = created_by;
    }

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


    public long getCreated_by_id() {
        return created_by_id;
    }

    public void setCreated_by_id(long created_by_id) {
        this.created_by_id = created_by_id;
    }

    public ArtistObject getArtist() {
        return artist;
    }

    public void setArtist(ArtistObject artist) {
        this.artist = artist;
    }

    public LocationObject getLocation() {
        return location;
    }

    public void setLocation(LocationObject location) {
        this.location = location;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public long getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(long artist_id) {
        this.artist_id = artist_id;
    }

    public long getLocation_id() {
        return location_id;
    }

    public void setLocation_id(long location_id) {
        this.location_id = location_id;
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

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getVoter_amount() {
        return voter_amount;
    }

    public void setVoter_amount(int voter_amount) {
        this.voter_amount = voter_amount;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }
}
