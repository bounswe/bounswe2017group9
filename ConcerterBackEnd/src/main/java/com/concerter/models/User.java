package com.concerter.models;

import org.joda.time.DateTime;

import java.util.List;

public class User {
    long id;
    long facebookID;
    long googleID;
    String name;
    String username;
    String email;
    String password;
    List<User> followers;
    List<User> followings;
    List<SemanticTag> favoriteTags;
    String photoPath;
    DateTime createAt;
    DateTime lastLogin;
    List<Review> reviews;
    List<Concert> attendedConcerts;
    List<Concert> willAttendConcerts;

    public String getName(){ return name;}

    public String getUsername() {
        return username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFacebookID() {
        return facebookID;
    }

    public void setFacebookID(long facebookID) {
        this.facebookID = facebookID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public List<User> getFollowings() {
        return followings;
    }

    public void setFollowings(List<User> followings) {
        this.followings = followings;
    }

    public List<SemanticTag> getFavoriteTags() {
        return favoriteTags;
    }

    public void setFavoriteTags(List<SemanticTag> favoriteTags) {
        this.favoriteTags = favoriteTags;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public DateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(DateTime createAt) {
        this.createAt = createAt;
    }

    public DateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(DateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Concert> getAttendedConcerts() {
        return attendedConcerts;
    }

    public void setAttendedConcerts(List<Concert> attendedConcerts) {
        this.attendedConcerts = attendedConcerts;
    }

    public List<Concert> getWillAttendConcerts() {
        return willAttendConcerts;
    }

    public void setWillAttendConcerts(List<Concert> willAttendConcerts) {
        this.willAttendConcerts = willAttendConcerts;
    }

    public long getGoogleID() {

        return googleID;
    }

    public void setGoogleID(long googleID) {
        this.googleID = googleID;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public void setName(String name){ this.name=name; }


}
