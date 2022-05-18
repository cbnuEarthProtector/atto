package com.example.atto.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.atto.database.dao.ProductDao;
import com.example.atto.database.entity.Product;

@Database(entities = {Product.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ProductDao productDao();
}
