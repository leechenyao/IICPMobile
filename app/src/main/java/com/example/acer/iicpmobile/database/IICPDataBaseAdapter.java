package com.example.acer.iicpmobile.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Acer on 2/18/2017.
 * This file require to handle all Database operation : Insert, Select
 */

public class IICPDataBaseAdapter {
    private Context mContext;
    public SQLiteDatabase mDatabase;
    private IICPBaseHelper dbHelper;

    public IICPDataBaseAdapter(Context context) {
        dbHelper = new IICPBaseHelper(context);
    }

    public IICPDataBaseAdapter Open() throws SQLException {
        mDatabase = dbHelper.getWritableDatabase();
        return this;
    }

    public void Close() {
        mDatabase.close();
    }

    public  SQLiteDatabase getDatabaseInstance() {
        return mDatabase;
    }

    public void Insert(String TableName, ContentValues values) {
        mDatabase.insert(TableName, null, values);
    }

    public String SelectOne(String tableName, String columnName, String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                tableName,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );
        if (cursor.getCount() < 1) { // NO DATA
            cursor.close();
            return "";
        }
        else {
            cursor.moveToFirst();
            String columnText = cursor.getString(cursor.getColumnIndex(columnName));
            cursor.close();
            return columnText;
        }
    }

    public Cursor SelectAll(String tableName, String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                tableName,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );
        return cursor;
    }
}
