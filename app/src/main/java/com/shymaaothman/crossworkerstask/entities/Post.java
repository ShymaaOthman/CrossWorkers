package com.shymaaothman.crossworkerstask.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Shymaa Othman on 7/23/2018.
 */

public class Post {

    private int id ;

    @SerializedName("name")
    private String postname;

    private Thumbnail thumbnail ;

    private List<Postperiods> postperiods;

    private List<Postfields>postfields ;

    private long expiry_counter ;

    public String getPostname() {
        return postname;
    }

    public void setPostname(String postname) {
        this.postname = postname;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public List<Postperiods> getPostperiods() {
        return postperiods;
    }

    public void setPostperiods(List<Postperiods> postperiods) {
        this.postperiods = postperiods;
    }

    public List<Postfields> getPostfields() {
        return postfields;
    }

    public void setPostfields(List<Postfields> postfields) {
        this.postfields = postfields;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getExpiry_counter() {
        return expiry_counter;
    }

    public void setExpiry_counter(long expiry_counter) {
        this.expiry_counter = expiry_counter;
    }
}
