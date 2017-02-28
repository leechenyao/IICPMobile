package com.example.acer.iicpmobile;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Acer on 2/19/2017.
 */

public class SaveSharedPreference {
    private static final String PREF_STUDENT_ID = "com.example.acer.iicpmobile.STUDENT_ID";

    static SharedPreferences getSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setStudentId(Context context, String student_id)
    {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(PREF_STUDENT_ID, student_id);
        editor.commit();
    }

    public static String getStudentId(Context context)
    {
        return getSharedPreferences(context).getString(PREF_STUDENT_ID, "");
    }

    public static void clearStudentId(Context context)
    {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.clear(); //clear all stored data
        editor.commit();
    }
}
