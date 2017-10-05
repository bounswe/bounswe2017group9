package com.concerter.models;

import java.util.List;

public class SemanticTag {
    String name;
    long id;
    List<SemanticTag> relatedTags;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<SemanticTag> getRelatedTags() {
        return relatedTags;
    }

    public void setRelatedTags(List<SemanticTag> relatedTags) {
        this.relatedTags = relatedTags;
    }
}
