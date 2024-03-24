package com.example.upmobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.upmobile.databinding.LaunchScreenBinding;
import com.example.upmobile.static_classies.Extensions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LaunchScreen extends AppCompatActivity {

    private LaunchScreenBinding binding;
    ConnDB connDB;
    Cursor cursor;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LaunchScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        connDB = new ConnDB(this);
        db = connDB.getReadableDatabase();
        if (Extensions.hasConnection(getApplicationContext())) {
            binding.progressBar.setVisibility(View.VISIBLE);
            getFoods();
            getDrinks();
            getSnacks();
            getSaucies();
        } else {
            binding.progressBar.setVisibility(View.GONE);
            startActivity(new Intent(LaunchScreen.this, MainScreen.class));
            finish();
        }
    }

    public void getFoods() {
        cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='foods';", null);
        //db.delete("foods", null, null);

        if (cursor != null) {
            if (!cursor.moveToFirst()) {
                Extensions.createTableFoods(getApplicationContext());
            }
            cursor.close();
        }
        FirebaseDatabase.getInstance().getReference().child("database").child("Foods").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    long count = snapshot.getChildrenCount();
                    db = connDB.getReadableDatabase();
                    cursor = db.rawQuery("SELECT COUNT(*) FROM foods", null);
                    int countSQLite;
                    if (cursor != null && cursor.moveToFirst()) {
                        countSQLite = cursor.getInt(0);
                        if (count > countSQLite) {
                            db.delete("foods", null, null);
                            for (DataSnapshot idd : snapshot.getChildren()) {
                                ContentValues cv = new ContentValues();
                                cv.put("name", idd.child("Name").getValue(String.class));
                                cv.put("price", idd.child("Price").getValue(String.class));
                                cv.put("img", idd.child("Image").getValue(String.class));
                                db.insert("foods", null, cv);
                            }
                            startActivity(new Intent(LaunchScreen.this, MainScreen.class));
                            finish();
                        } else {
                            startActivity(new Intent(LaunchScreen.this, MainScreen.class));
                            finish();
                        }
                    }

                    if (cursor != null) {
                        cursor.close();
                    }
                    db.close();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Error while foods loading", error.getMessage());
            }
        });
    }

    public void getDrinks(){
        cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='drinks';", null);

        if (cursor != null) {
            if (!cursor.moveToFirst()) {
                Extensions.createTableDrinks(getApplicationContext());
            }
            cursor.close();
        }

        FirebaseDatabase.getInstance().getReference().child("database").child("Drinks").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    long count = snapshot.getChildrenCount();
                    db = connDB.getReadableDatabase();
                    cursor = db.rawQuery("SELECT COUNT(*) FROM drinks", null);
                    int countSQLite;
                    if (cursor != null && cursor.moveToFirst()) {
                        countSQLite = cursor.getInt(0);
                        if (count > countSQLite) {
                            db.delete("drinks", null, null);
                            for (DataSnapshot idd : snapshot.getChildren()) {
                                ContentValues cv = new ContentValues();
                                cv.put("name", idd.child("Name").getValue(String.class));
                                cv.put("price", idd.child("Price").getValue(String.class));
                                cv.put("img", idd.child("Image").getValue(String.class));
                                db.insert("drinks", null, cv);

                            }
                            startActivity(new Intent(LaunchScreen.this, MainScreen.class));
                            finish();
                        } else {
                            startActivity(new Intent(LaunchScreen.this, MainScreen.class));
                            finish();
                        }
                    }

                    if (cursor != null) {
                        cursor.close();
                    }
                    db.close();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Error while drinks loading", error.getMessage());
            }
        });
    }

    public void getSaucies(){
        cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='sauce';", null);

        if (cursor != null) {
            if (!cursor.moveToFirst()) {
                Extensions.createTableSauce(getApplicationContext());
            }
            cursor.close();
        }
        FirebaseDatabase.getInstance().getReference().child("database").child("Sauce").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    long count = snapshot.getChildrenCount();
                    db = connDB.getReadableDatabase();
                    cursor = db.rawQuery("SELECT COUNT(*) FROM sauce", null);
                    int countSQLite;
                    if (cursor != null && cursor.moveToFirst()) {
                        countSQLite = cursor.getInt(0);
                        if (count > countSQLite) {
                            db.delete("sauce", null, null);
                            for (DataSnapshot idd : snapshot.getChildren()) {
                                ContentValues cv = new ContentValues();
                                cv.put("name", idd.child("Name").getValue(String.class));
                                cv.put("price", idd.child("Price").getValue(String.class));
                                cv.put("img", idd.child("Image").getValue(String.class));
                                db.insert("sauce", null, cv);

                            }
                            startActivity(new Intent(LaunchScreen.this, MainScreen.class));
                            finish();
                        } else {
                            startActivity(new Intent(LaunchScreen.this, MainScreen.class));
                            finish();
                        }
                    }

                    if (cursor != null) {
                        cursor.close();
                    }
                    db.close();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Error while saucies loading", error.getMessage());
            }
        });
    }

    public void getSnacks(){
        cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='snacks';", null);

        if (cursor != null) {
            if (!cursor.moveToFirst()) {
                Extensions.createTableSnacks(getApplicationContext());
            }
            cursor.close();
        }
        FirebaseDatabase.getInstance().getReference().child("database").child("Snacks").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    long count = snapshot.getChildrenCount();
                    db = connDB.getReadableDatabase();
                    cursor = db.rawQuery("SELECT COUNT(*) FROM snacks", null);
                    int countSQLite;
                    if (cursor != null && cursor.moveToFirst()) {
                        countSQLite = cursor.getInt(0);
                        if (count > countSQLite) {
                            db.delete("snacks", null, null);
                            for (DataSnapshot idd : snapshot.getChildren()) {
                                ContentValues cv = new ContentValues();
                                cv.put("name", idd.child("Name").getValue(String.class));
                                cv.put("price", idd.child("Price").getValue(String.class));
                                cv.put("img", idd.child("Image").getValue(String.class));
                                db.insert("snacks", null, cv);

                            }
                            startActivity(new Intent(LaunchScreen.this, MainScreen.class));
                            finish();
                        } else {
                            startActivity(new Intent(LaunchScreen.this, MainScreen.class));
                            finish();
                        }
                    }

                    if (cursor != null) {
                        cursor.close();
                    }
                    db.close();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Error while snacks loading", error.getMessage());
            }
        });
    }
}