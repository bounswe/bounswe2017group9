package com.example.abtasdan.listviewtutorial.modals;

/**
 * Created by abtasdan on 29.11.2017.
 */

public class CreatedCommentObject {
    long id;
    long commented_by;
    UserForCommentObject commented_user;
    long concert_id;
    long up_votes;
    long down_votes;
    String comment;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCommented_by() {
        return commented_by;
    }

    public void setCommented_by(long commented_by) {
        this.commented_by = commented_by;
    }

    public UserForCommentObject getCommented_user() {
        return commented_user;
    }

    public void setCommented_user(UserForCommentObject commented_user) {
        this.commented_user = commented_user;
    }

    public long getConcert_id() {
        return concert_id;
    }

    public void setConcert_id(long concert_id) {
        this.concert_id = concert_id;
    }

    public long getUp_votes() {
        return up_votes;
    }

    public void setUp_votes(long up_votes) {
        this.up_votes = up_votes;
    }

    public long getDown_votes() {
        return down_votes;
    }

    public void setDown_votes(long down_votes) {
        this.down_votes = down_votes;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
