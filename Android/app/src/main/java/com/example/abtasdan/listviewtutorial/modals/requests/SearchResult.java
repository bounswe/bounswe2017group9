package com.example.abtasdan.listviewtutorial.modals.requests;

import com.example.abtasdan.listviewtutorial.modals.ArtistObject;
import com.example.abtasdan.listviewtutorial.modals.Concert;
import com.example.abtasdan.listviewtutorial.modals.CreatedBy;

import java.util.ArrayList;

/**
 * Created by abtasdan on 30.11.2017.
 */

public class SearchResult {

    ArrayList<CreatedBy> users;
    ArrayList<Concert> concerts;
    ArrayList<ArtistObject> artistObjects;

    public ArrayList<CreatedBy> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<CreatedBy> users) {
        this.users = users;
    }

    public ArrayList<Concert> getConcerts() {
        return concerts;
    }

    public void setConcerts(ArrayList<Concert> concerts) {
        this.concerts = concerts;
    }

    public ArrayList<ArtistObject> getArtistObjects() {
        return artistObjects;
    }

    public void setArtistObjects(ArrayList<ArtistObject> artistObjects) {
        this.artistObjects = artistObjects;
    }
}
