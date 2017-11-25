package com.cmpe451.group9.concerterdroid.Classes;

import java.io.Serializable;

/**
 * Created by Necip on 18.11.2017.
 */

public class Artist implements Serializable {
    private int id;
    private String name;

    public Artist(String name) {
        this.name = name;
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
}
