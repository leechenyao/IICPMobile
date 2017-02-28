package com.example.acer.iicpmobile.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Acer on 2/18/2017.
 */

public class IICPBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "iicpBase.db";

    public IICPBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table student( student_id text primary key,password text, name text, school text, course text, semester text, phone_no text); ");
        db.execSQL("create table news( news_title text, news_description text, news_date text, news_by text); ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
