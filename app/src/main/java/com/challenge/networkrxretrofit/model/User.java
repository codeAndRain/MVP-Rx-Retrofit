package com.challenge.networkrxretrofit.model;

import com.google.gson.annotations.SerializedName;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {

    @PrimaryKey
    @SerializedName("id")
    private int id_;
    private int userId;
    private String title;
    private boolean completed;

    /**
     * No args constructor for use in serialization
     *
     */
    public User() {
    }

    /**
     *
     * @param id
     * @param title
     * @param userId
     * @param completed
     */
    public User(int userId, int id, String title, boolean completed) {
        super();
        this.id_ = userId;
        this.userId = id;
        this.title = title;
        this.completed = completed;
    }

    public int getId_() {
        return id_;
    }

    public void setId_(int id_) {
        this.id_ = id_;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

}

