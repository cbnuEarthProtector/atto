package com.example.atto.database;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ProductBookmark.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {
    private static final String DB_NAME = "userDatabase.db";
    private static volatile UserDatabase instance;

    public static synchronized UserDatabase getInstance(Context context) {
        Log.v("db", "get database");
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static UserDatabase create(final Context context) {
        Log.v("db", "create database");
        return Room.databaseBuilder(context, UserDatabase.class, DB_NAME)
                .allowMainThreadQueries()
                .build();
    }

    public abstract ProductBookmarkDao productBookmarkDao();
}
