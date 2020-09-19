package com.example.hw11.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class Task {
    private String mTitle;
    private String Description;
    private String mState;
    private Date mDate;
    private UUID mId;

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getState() {
        return mState;
    }

    public void setState(String state) {
        mState = state;
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public Task(String title, String description, String state, Date date) {
        mTitle = title;
        Description = description;
        mState = state;
        mDate = date;
        mId = UUID.randomUUID();
    }
}
