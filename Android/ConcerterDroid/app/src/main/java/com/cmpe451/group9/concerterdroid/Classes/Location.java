package com.cmpe451.group9.concerterdroid.Classes;

import java.io.Serializable;

/**
 * Created by Necip on 18.11.2017.
 */

public class Location implements Serializable {
    private int id;
    private double longitude;
    private double lattitude;
    private String city;
    private String address;

    public Location(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLattitude() {
        return lattitude;
    }

    public void setLattitude(double lattitude) {
        this.lattitude = lattitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
