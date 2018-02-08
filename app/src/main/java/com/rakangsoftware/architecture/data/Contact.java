package com.rakangsoftware.architecture.data;

public class Contact {

    private int    mId;
    private String mName;
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
