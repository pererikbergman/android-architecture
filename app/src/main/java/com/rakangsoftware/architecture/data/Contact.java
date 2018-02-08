package com.rakangsoftware.architecture.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "contacts")
public class Contact {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int    mId;

    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = "surename")
    private String mSurename;

    public int getId() {
        return mId;
    }

    public void setId(final int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(final String name) {
        mName = name;
    }

    public String getSurename() {
        return mSurename;
    }

    public void setSurename(final String surename) {
        mSurename = surename;
    }
}
