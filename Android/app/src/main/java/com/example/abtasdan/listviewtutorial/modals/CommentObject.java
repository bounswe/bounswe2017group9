package com.example.abtasdan.listviewtutorial.modals;

/**
 * Created by abtasdan on 29.11.2017.
 */

public class CommentObject {
    String comment;
    int comment_id;
    int comment_by;
    int category;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public int getComment_by() {
        return comment_by;
    }

    public void setComment_by(int comment_by) {
        this.comment_by = comment_by;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}
