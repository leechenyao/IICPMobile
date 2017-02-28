package com.example.acer.iicpmobile.Fragment;

/**
 * Created by Acer on 2/15/2017.
 */

public class News {
    private String mTitle, mDescription, mDate, mBy;

    public News(String title, String description, String date, String by) {
        mTitle = title;
        mDescription = description;
        mBy = by;
        mDate = date;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getBy() {
        return mBy;
    }

    public String getDate() {
        return mDate;
    }
}