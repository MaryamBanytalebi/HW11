package com.example.hw11.model;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {
    private String mName;
    private int mPhoto;
    private Date mDate;

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getPhoto() {
        return mPhoto;
    }

    public void setPhoto(int photo) {
        mPhoto = photo;
    }
}
