package com.example.abtasdan.listviewtutorial.modals;

/**
 * Created by abtasdan on 2.11.2017.
 */

public class CreatedBy {
    long id;
    String name;
    String email;
    int followers;
    int followings;
    String photo_path;
    String created_at;
    String updated_at;
    String last_login;

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public void setFollowings(int followings) {
        this.followings = followings;
    }

    public String getPhoto_path() {
        return photo_path;
    }

    public void setPhoto_path(String photo_path) {
        this.photo_path = photo_path;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public void setLast_login(String last_login) {
        this.last_login = last_login;
    }

    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public long getId() {
        return id;
    }
    public int getFollowers() {
        return followers;
    }
    public int getFollowings() {
        return followings;
    }
    public String getCreated_at() {
        return created_at;
    }
    public String getUpdated_at() {
        return updated_at;
    }
    public String getLast_login() {
        return last_login;
    }


}
