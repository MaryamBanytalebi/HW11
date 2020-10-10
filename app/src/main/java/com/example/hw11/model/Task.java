package com.example.hw11.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
@Entity(tableName = "taskTable")
public class Task {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long primaryId;
    @ColumnInfo(name = "title")
    private String mTitle;
    @ColumnInfo(name = "description")
    private String Description;
    @ColumnInfo(name = "state")
    private String mState;
    @ColumnInfo(name = "date")
    private Date mDate;
    @ColumnInfo(name = "uuid")
    private UUID mId;

    public long getPrimaryId() {
        return primaryId;
    }

    public void setPrimaryId(long primaryId) {
        this.primaryId = primaryId;
    }

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

    public Task(){ }
}
