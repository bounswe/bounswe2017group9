package com.example.abtasdan.listviewtutorial.modals;

/**
 * Created by abtasdan on 2.11.2017.
 */

public class LocationObject {
    long id;
    double longitude;
    double latitude;
    String city;
    String address;

    public long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }
    public String getCity() {
        return city;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
