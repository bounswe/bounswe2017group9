package com.example.abtasdan.listviewtutorial.modals;

/**
 * Created by abtasdan on 29.11.2017.
 */

public class CommentObject {
    String comment;
    long comment_id;
    long comment_by;
    long category;
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getComment_id() {
        return comment_id;
    }

    public void setComment_id(long comment_id) {
        this.comment_id = comment_id;
    }

    public long getComment_by() {
        return comment_by;
    }

    public void setComment_by(long comment_by) {
        this.comment_by = comment_by;
    }

    public long getCategory() {
        return category;
    }

    public void setCategory(long category) {
        this.category = category;
    }



}
