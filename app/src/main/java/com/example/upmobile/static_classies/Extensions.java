package com.example.upmobile.static_classies;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.upmobile.ConnDB;

public class Extensions {
    public static void AlertDialog(String title, String message, Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setNegativeButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public static boolean hasConnection(final Context context)
    {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        wifiInfo = cm.getActiveNetworkInfo();
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        return false;
    }

    private static ConnDB connDB;
    private static SQLiteDatabase db;

    public static void createTableFoods(Context context){
        connDB = new ConnDB(context);
        db = connDB.getWritableDatabase();
        db.execSQL("CREATE TABLE foods ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name" + " TEXT,"
                + "price" + " TEXT,"
                + "img" + " TEXT"
                + ")");
    }

    public static void createTableDrinks(Context context){
        connDB = new ConnDB(context);
        db = connDB.getWritableDatabase();
        db.execSQL("CREATE TABLE drinks ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name" + " TEXT,"
                + "price" + " TEXT,"
                + "img" + " TEXT"
                + ")");
    }

    public static void createTableSauce(Context context){
        connDB = new ConnDB(context);
        db = connDB.getWritableDatabase();
        db.execSQL("CREATE TABLE snacks ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name" + " TEXT,"
                + "price" + " TEXT,"
                + "img" + " TEXT"
                + ")");
    }

    public static void createTableSnacks(Context context){
        connDB = new ConnDB(context);
        db = connDB.getWritableDatabase();
        db.execSQL("CREATE TABLE sauce ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name" + " TEXT,"
                + "price" + " TEXT,"
                + "img" + " TEXT"
                + ")");
    }
}
