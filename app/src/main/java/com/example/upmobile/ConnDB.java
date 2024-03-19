package com.example.upmobile;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConnDB extends SQLiteOpenHelper {

    public ConnDB(Context context){
        super(context, "upMobile", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE tablena ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name" + " TEXT,"
                + "price" + " TEXT,"
                + "img" + " TEXT"
                + ")";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
