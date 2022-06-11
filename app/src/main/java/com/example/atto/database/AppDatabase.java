package com.example.atto.database;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Product.class, Brand.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DB_NAME = "appDatabase.db";
    private static volatile AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static AppDatabase create(final Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, DB_NAME)
                .createFromAsset("database/app.db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    public abstract ProductDao productDao();

    public abstract BrandDao brandDao();
}
