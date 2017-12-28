package com.example.abtasdan.listviewtutorial.modals;

import java.io.Serializable;

/**
 * Created by abtasdan on 2.11.2017.
 */

public class ArtistObject  implements Serializable{
    long id;
    String name;

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}
